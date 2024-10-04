package com.arabook.arabook.external.jwt.provider;

import java.util.List;

public interface JwtTokenProvider {
  String createAccessToken(String memberId, List<String> roles);

  String createRefreshToken();
}
