package com.arabook.arabook.external.jwt.provider;

import java.util.List;

public interface JwtTokenProvider {
  String createToken(String memberId, List<String> roles);

  String createRefreshToken();
}
