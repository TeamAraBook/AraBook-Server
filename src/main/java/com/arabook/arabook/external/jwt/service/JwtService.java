package com.arabook.arabook.external.jwt.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import com.arabook.arabook.api.auth.controller.dto.request.IssueTokenRequest;
import com.arabook.arabook.api.auth.controller.dto.response.IssueTokenResponse;

public interface JwtService {
  IssueTokenResponse issueToken(String memberId, List<String> roles);

  IssueTokenResponse reissueToken(IssueTokenRequest request);

  String extractAccessToken(HttpServletRequest request);

  String extractRefreshToken(HttpServletRequest request);
}
