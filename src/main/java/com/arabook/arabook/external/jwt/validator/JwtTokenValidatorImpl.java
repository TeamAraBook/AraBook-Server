package com.arabook.arabook.external.jwt.validator;

import static com.arabook.arabook.common.exception.auth.AuthExceptionType.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arabook.arabook.common.exception.auth.AuthException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenValidatorImpl implements JwtTokenValidator {

  @Value("${jwt.secretKey}")
  private String secretKey;

  private JWTVerifier getVerifier() {
    return JWT.require(Algorithm.HMAC512(secretKey)).acceptExpiresAt(0).build();
  }

  @Override
  public void isValidToken(String token) {
    try {
      getVerifier().verify(token);
    } catch (TokenExpiredException e) {
      throw new AuthException(UNAUTHORIZED_TOKEN);
    } catch (SignatureVerificationException | JWTDecodeException e) {
      throw new AuthException(INVALID_TOKEN);
    }
  }

  @Override
  public Claim getClaim(String token, String claim) {
    return getVerifier().verify(token).getClaim(claim);
  }
}
