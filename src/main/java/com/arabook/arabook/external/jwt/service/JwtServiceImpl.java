package com.arabook.arabook.external.jwt.service;

import static com.arabook.arabook.common.exception.auth.AuthExceptionType.*;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arabook.arabook.api.auth.controller.dto.request.IssueTokenRequest;
import com.arabook.arabook.api.auth.controller.dto.response.IssueTokenResponse;
import com.arabook.arabook.common.exception.auth.AuthException;
import com.arabook.arabook.external.jwt.provider.JwtTokenProvider;
import com.arabook.arabook.external.jwt.validator.JwtTokenValidator;
import com.arabook.arabook.storage.domain.member.entity.enums.Role;
import com.arabook.arabook.storage.redis.repository.RedisTokenRepository;
import com.arabook.arabook.storage.redis.repository.dto.RefreshTokenDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Getter
public class JwtServiceImpl implements JwtService {
  private static final String BEARER = "Bearer ";
  private static final String ID_CLAIM = "memberId";
  private static final String ROLE_CLAIM = "roles";

  @Value("${jwt.access.header}")
  private String accessHeader;

  @Value("${jwt.refresh.header}")
  private String refreshHeader;

  private final RedisTokenRepository redisTokenRepository;

  private final JwtTokenProvider jwtTokenProvider;
  private final JwtTokenValidator jwtTokenValidator;

  @Override
  public IssueTokenResponse issueToken(String memberId, List<String> roles) {
    String accessToken = jwtTokenProvider.createAccessToken(memberId, roles);

    boolean isServiceMember =
        roles.stream().anyMatch(role -> role.equals(Role.USER) || role.equals(Role.GUEST));

    if (isServiceMember) {
      String refreshToken = jwtTokenProvider.createRefreshToken();
      updateRefreshToken(memberId, refreshToken);
      return IssueTokenResponse.of(accessToken, refreshToken);
    }

    throw new AuthException(INVALID_REQUEST_LOGIN);
  }

  @Override
  public IssueTokenResponse reissueToken(IssueTokenRequest request) {
    String accessToken = request.accessToken();
    String refreshToken = request.refreshToken();

    jwtTokenValidator.validateToken(refreshToken);

    String memberId = getMemberIdFromAccessToken(accessToken);
    RefreshTokenDTO foundRefreshToken = getRefreshTokenForMemberId(memberId);

    validateRefreshTokenMatch(refreshToken, foundRefreshToken);

    List<String> roles = getRolesFromRefreshToken(refreshToken);

    String newAccessToken = jwtTokenProvider.createAccessToken(memberId, roles);
    String newRefreshToken = jwtTokenProvider.createRefreshToken();

    updateRefreshToken(memberId, newRefreshToken);

    return IssueTokenResponse.of(newAccessToken, newRefreshToken);
  }

  private String getMemberIdFromAccessToken(String accessToken) {
    return jwtTokenValidator.getClaim(accessToken, ID_CLAIM).asString();
  }

  private RefreshTokenDTO getRefreshTokenForMemberId(String memberId) {
    return redisTokenRepository.findByMemberIdOrElseThrowException(memberId);
  }

  private void validateRefreshTokenMatch(String refreshToken, RefreshTokenDTO foundRefreshToken) {
    if (!refreshToken.equals(foundRefreshToken.getRefreshToken())) {
      throw new AuthException(INVALID_TOKEN);
    }
  }

  private List<String> getRolesFromRefreshToken(String accessToken) {
    return jwtTokenValidator.getClaim(accessToken, ROLE_CLAIM).asList(String.class);
  }

  private void updateRefreshToken(String memberId, String newRefreshToken) {
    RefreshTokenDTO refreshTokenDTO = redisTokenRepository.findByMemberId(memberId).orElse(null);

    if (refreshTokenDTO != null) {
      refreshTokenDTO.updateRefreshToken(newRefreshToken);
      redisTokenRepository.save(refreshTokenDTO);
      return;
    }

    redisTokenRepository.save(
        RefreshTokenDTO.builder()
            .memberId(String.valueOf(memberId))
            .refreshToken(newRefreshToken)
            .build());
  }

  @Override
  public String extractAccessToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(accessHeader))
        .filter(accessToken -> accessToken.startsWith(BEARER))
        .map(accessToken -> accessToken.replace(BEARER, ""))
        .orElseThrow(() -> new AuthException(INVALID_TOKEN));
  }

  @Override
  public String extractRefreshToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(refreshHeader))
        .filter(refreshToken -> refreshToken.startsWith(BEARER))
        .map(refreshToken -> refreshToken.replace(BEARER, ""))
        .orElseThrow(() -> new AuthException(INVALID_TOKEN));
  }
}
