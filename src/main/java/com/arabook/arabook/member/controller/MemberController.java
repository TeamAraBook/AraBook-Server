package com.arabook.arabook.member.controller;

import static com.arabook.arabook.common.success.member.MemberSuccessType.*;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.arabook.arabook.common.response.ResponseTemplate;
import com.arabook.arabook.common.security.AuthMember;
import com.arabook.arabook.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.member.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberApi {
  private final MemberService memberService;

  @Override
  @PutMapping("/onboarding")
  public ResponseEntity<ResponseTemplate> onboarding(
      @RequestBody @Valid final MemberOnboardingRequest request, @AuthMember final Long memberId) {
    memberService.onboarding(request, memberId);
    return ResponseEntity.ok(ResponseTemplate.success(ONBOARDING_SUCCESS));
  }

  @Override
  @DeleteMapping("/withdraw")
  public ResponseEntity<ResponseTemplate> withdraw(@AuthMember final Long memberId) {
    memberService.withdraw(memberId);
    return ResponseEntity.ok(ResponseTemplate.success(WITHDRAW_SUCCESS));
  }
}
