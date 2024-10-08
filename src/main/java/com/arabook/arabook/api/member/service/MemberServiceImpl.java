package com.arabook.arabook.api.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final MemberSubCategorySelectionService memberCategorySelectionSerivce;

  @Override
  @Transactional
  public void onboarding(final MemberOnboardingRequest request, final Long memberId) {
    Member member = memberRepository.findByMemberIdOrThrow(memberId);
    member.updateOnboardingInfo(request.gender(), request.age());
    memberCategorySelectionSerivce.selectSubCategories(member, request.interestCategoryIds());
  }
}
