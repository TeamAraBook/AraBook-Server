package com.arabook.arabook.api.global.filter;

import static com.arabook.arabook.api.global.config.SecurityConfig.*;
import static com.arabook.arabook.common.exception.auth.AuthExceptionType.*;

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

import com.arabook.arabook.api.global.security.MemberAuthentication;
import com.arabook.arabook.common.exception.auth.AuthException;
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

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (Arrays.stream(AUTH_WHITELIST)
        .anyMatch(whiteUrl -> request.getRequestURI().equals(whiteUrl))) {
      filterChain.doFilter(request, response);
      return;
    }

    if (Arrays.stream(AUTH_WHITELIST_WILDCARD)
        .anyMatch(
            whiteUrl ->
                request.getRequestURI().startsWith(whiteUrl.substring(0, whiteUrl.length() - 3)))) {
      filterChain.doFilter(request, response);
      return;
    }

    String accessToken = jwtService.extractAccessToken(request);
    boolean isNotContainsAccessToken = accessToken == null;
    if (isNotContainsAccessToken) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      jwtTokenValidator.validateToken(accessToken);

      String memberId = jwtService.getMemberIdFromAccessToken(accessToken);
      List<String> roles = jwtService.getRolesFromAccessToken(accessToken);
      Collection<? extends GrantedAuthority> authorities = getAuthoritiesFromList(roles);
      MemberAuthentication memberAuthentication =
          new MemberAuthentication(memberId, null, authorities);

      log.info("Authentication Principal : {}", memberAuthentication.getPrincipal());

      SecurityContextHolder.getContext().setAuthentication(memberAuthentication);
    } catch (AuthException e) {
      throw new AuthException(e.getExceptionType());
    }

    filterChain.doFilter(request, response);
  }

  private Collection<? extends GrantedAuthority> getAuthoritiesFromList(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
