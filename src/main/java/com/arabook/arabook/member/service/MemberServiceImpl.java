package com.arabook.arabook.member.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.member.controller.dto.request.MemberOnboardingRequest;
import com.arabook.arabook.member.entity.*;
import com.arabook.arabook.member.repository.*;
import com.arabook.arabook.review.repository.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final ReviewRepository reviewRepository;
  private final MemberSubCategorySelectionRepository memberSubCategorySelectionRepository;
  private final MemberSubCategorySelectionService memberCategorySelectionService;
  private final AIRecommendationRepository aiRecommendationRepository;

  @Override
  @Transactional
  public void onboarding(final MemberOnboardingRequest request, final Long memberId) {
    Member member = memberRepository.findByMemberIdOrThrow(memberId);
    int age = member.calculateAge(request.birthYear());
    member.updateOnboardingInfo(request.nickname(), request.gender(), age);
    memberCategorySelectionService.selectSubCategories(member, request.interestSubCategoryIds());
  }

  @Override
  @Transactional
  public void withdraw(final Long memberId) {
    Member member = memberRepository.findByMemberIdOrThrow(memberId);
    memberSubCategorySelectionRepository.deleteByMember(member);
    reviewRepository.deleteByReviewer(member);
    aiRecommendationRepository.deleteByMember(member);
    memberRepository.delete(member);
  }
}
