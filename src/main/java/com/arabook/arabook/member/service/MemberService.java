package com.arabook.arabook.member.service;

import com.arabook.arabook.member.controller.dto.request.MemberOnboardingRequest;

public interface MemberService {
  void onboarding(MemberOnboardingRequest request, Long memberId);
}
