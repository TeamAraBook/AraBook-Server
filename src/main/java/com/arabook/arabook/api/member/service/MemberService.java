package com.arabook.arabook.api.member.service;

import com.arabook.arabook.api.member.controller.dto.request.MemberOnboardingRequest;

public interface MemberService {
  void onboarding(MemberOnboardingRequest request, Long memberId);
}
