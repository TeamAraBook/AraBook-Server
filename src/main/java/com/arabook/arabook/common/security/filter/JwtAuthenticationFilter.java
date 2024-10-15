package com.arabook.arabook.common.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.arabook.arabook.common.exception.auth.AuthException;
import com.arabook.arabook.common.security.MemberAuthentication;
import com.arabook.arabook.external.jwt.service.JwtService;
import com.arabook.arabook.external.jwt.validator.JwtTokenValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenValidator jwtTokenValidator;
  private final JwtService jwtService;

  private static final String AI_RECOMMEND_PATH = "/recommend/ai";
  private static final int START_WILDCARD_INDEX = 0;
  private static final int END_WILDCARD_INDEX = 3;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestUri = request.getRequestURI();

    if (isWhitelisted(requestUri)) {
      filterChain.doFilter(request, response);
      return;
    }

    if (requestUri.equals(AI_RECOMMEND_PATH)) {
      handleAIRecommendPath(request, response, filterChain);
      return;
    }

    handleTokenAuthentication(request, response, filterChain);
  }

  private boolean isWhitelisted(String requestUri) {
    return Arrays.asList(AUTH_WHITELIST).contains(requestUri)
        || Arrays.stream(AUTH_WHITELIST_WILDCARD)
            .anyMatch(
                whiteUrl ->
                    requestUri.startsWith(
                        whiteUrl.substring(
                            START_WILDCARD_INDEX, whiteUrl.length() - END_WILDCARD_INDEX)));
  }

  private void handleAIRecommendPath(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      authenticateRequest(request);
    } catch (AuthException e) {
      SecurityContextHolder.getContext().setAuthentication(createAnonymousAuthentication());
    }
    filterChain.doFilter(request, response);
  }

  private MemberAuthentication createAnonymousAuthentication() {
    Collection<? extends GrantedAuthority> authorities =
        List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    return new MemberAuthentication("anonymousUser", null, authorities); // 익명 유저 생성
  }

  private void handleTokenAuthentication(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      authenticateRequest(request);
    } catch (AuthException e) {
      throw new AuthException(e.getExceptionType());
    }
    filterChain.doFilter(request, response);
  }

  private void authenticateRequest(HttpServletRequest request) {
    String accessToken = jwtService.extractAccessToken(request);
    jwtTokenValidator.validateToken(accessToken);

    String memberId = jwtService.getMemberIdFromAccessToken(accessToken);
    List<String> roles = jwtService.getRolesFromAccessToken(accessToken);
    Collection<? extends GrantedAuthority> authorities = getAuthoritiesFromList(roles);
    MemberAuthentication memberAuthentication =
        new MemberAuthentication(memberId, null, authorities);

    log.info("Authentication Principal : {}", memberAuthentication.getPrincipal());

    SecurityContextHolder.getContext().setAuthentication(memberAuthentication);
  }

  private Collection<? extends GrantedAuthority> getAuthoritiesFromList(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
