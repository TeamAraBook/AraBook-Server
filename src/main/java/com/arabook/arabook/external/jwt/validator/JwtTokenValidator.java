package com.arabook.arabook.external.jwt.validator;

import com.auth0.jwt.interfaces.Claim;

public interface JwtTokenValidator {
  void isValidToken(String token);

  Claim getClaim(String token, String claim);
}
