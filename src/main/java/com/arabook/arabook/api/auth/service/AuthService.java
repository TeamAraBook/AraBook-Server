package com.arabook.arabook.api.auth.service;

import com.arabook.arabook.api.auth.controller.dto.request.AuthRequest;
import com.arabook.arabook.api.auth.controller.dto.response.AuthResponse;

public interface AuthService {
  AuthResponse signUpOrLogin(AuthRequest request);
  // IssueTokenResponse issueToken(IssueTokenRequest request);
}
