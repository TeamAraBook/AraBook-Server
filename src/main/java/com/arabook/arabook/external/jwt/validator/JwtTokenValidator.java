package com.arabook.arabook.external.jwt.validator;

import com.auth0.jwt.interfaces.Claim;

public interface JwtTokenValidator {
  void validateToken(String token);

  Claim getClaim(String token, String claim);
}
