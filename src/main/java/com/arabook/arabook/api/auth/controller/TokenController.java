package com.arabook.arabook.api.auth.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.auth.controller.dto.response.IssueTokenResponse;
import com.arabook.arabook.external.jwt.service.JwtService;

import lombok.RequiredArgsConstructor;

@Profile("local")
@RestController
@RequiredArgsConstructor
public class TokenController {
  private final JwtService jwtService;

  @GetMapping("/test/token")
  public ResponseEntity<IssueTokenResponse> issueTestToken() {
    List<String> roles = Arrays.asList("USER");
    IssueTokenResponse response = jwtService.issueToken("1", roles);
    return ResponseEntity.ok(response);
  }
}
