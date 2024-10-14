package com.arabook.arabook.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.member.repository.MemberRepository;

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
    int age = member.calculateAge(request.birthYear());
    member.updateOnboardingInfo(request.nickname(), request.gender(), age);
    memberCategorySelectionSerivce.selectSubCategories(member, request.interestSubCategoryIds());
  }
}
