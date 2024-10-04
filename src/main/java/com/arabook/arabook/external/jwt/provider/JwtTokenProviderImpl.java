package com.arabook.arabook.external.jwt.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {
  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.access.expiration}")
  private Long accessTokenExpirationPeriod;

  @Value("${jwt.refresh.expiration}")
  private Long refreshTokenExpirationPeriod;

  private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
  private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
  private static final String ID_CLAIM = "memberId";
  private static final String ROLE_CLAIM = "roles";

  @Override
  public String createAccessToken(String memberId, List<String> roles) {
    Date now = new Date();

    return JWT.create()
        .withSubject(ACCESS_TOKEN_SUBJECT)
        .withClaim(ID_CLAIM, memberId)
        .withClaim(ROLE_CLAIM, roles)
        .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }

  @Override
  public String createRefreshToken() {
    Date now = new Date();

    return JWT.create()
        .withSubject(REFRESH_TOKEN_SUBJECT)
        .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }
}
