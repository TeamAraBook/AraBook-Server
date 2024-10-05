package com.arabook.arabook.api.member.controller;

import static com.arabook.arabook.common.success.member.MemberSuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.global.security.AuthMember;
import com.arabook.arabook.api.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.api.member.service.MemberServiceImpl;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
  private final MemberServiceImpl memberService;

  @Override
  @PutMapping("/onboarding")
  public ResponseEntity<ResponseTemplate> onboarding(
      @RequestBody final MemberOnboardingRequest request, @AuthMember final Long memberId) {
    memberService.onboarding(request, memberId);
    return ResponseEntity.ok(ResponseTemplate.success(ONBOARDING_SUCCESS));
  }
}
