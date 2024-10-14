package com.arabook.arabook.api.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.auth.controller.dto.request.AuthRequest;
import com.arabook.arabook.api.auth.controller.dto.response.AuthResponse;
import com.arabook.arabook.api.auth.controller.dto.response.IssueTokenResponse;
import com.arabook.arabook.api.auth.service.vo.AuthMemberVO;
import com.arabook.arabook.external.jwt.service.JwtService;
import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {
  private final MemberRepository memberRepository;
  private final JwtService jwtTokenService;
  private final AuthServiceProvider authServiceProvider;

  @Override
  @Transactional
  public AuthResponse signUpOrLogin(AuthRequest request) {
    SocialAuthService authService = authServiceProvider.getAuthService(request.platformType());
    AuthMemberVO authMemberVO = authService.getUserInfo(request.socialToken());
    Member member = findOrSaveMember(authMemberVO);
    String memberId = member.getMemberId().toString();
    List<String> roles = List.of(member.getRole().name());
    IssueTokenResponse issueTokenResponse = jwtTokenService.issueToken(memberId, roles);
    return new AuthResponse(member.getMemberId(), member.getRole(), issueTokenResponse);
  }

  private Member findOrSaveMember(AuthMemberVO authMemberVO) {
    return memberRepository
        .findBySocialPlatformId(authMemberVO.platformId())
        .orElseGet(() -> memberRepository.save(authMemberVO.toEntity()));
  }
}
