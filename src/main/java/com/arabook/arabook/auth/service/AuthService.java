package com.arabook.arabook.auth.service;

import com.arabook.arabook.auth.controller.dto.request.AuthRequest;
import com.arabook.arabook.auth.controller.dto.response.AuthResponse;

public interface AuthService {
  AuthResponse signUpOrLogin(AuthRequest request);
  // IssueTokenResponse issueToken(IssueTokenRequest request);
}
