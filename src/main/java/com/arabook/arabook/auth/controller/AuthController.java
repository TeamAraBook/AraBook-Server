package com.arabook.arabook.auth.controller;

import static com.arabook.arabook.common.success.member.MemberSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.auth.controller.dto.request.AuthRequest;
import com.arabook.arabook.auth.controller.dto.response.AuthResponse;
import com.arabook.arabook.auth.service.AuthService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
  private final AuthService authService;

  @PostMapping("/social-login")
  public ResponseEntity<ResponseTemplate<AuthResponse>> signUpOrLogin(
      @RequestBody AuthRequest request) {
    AuthResponse response = authService.signUpOrLogin(request);
    return ResponseEntity.ok(ResponseTemplate.success(ONBOARDING_SUCCESS, response));
  }
}
