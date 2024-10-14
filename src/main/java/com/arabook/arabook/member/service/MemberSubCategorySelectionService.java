package com.arabook.arabook.member.service;

import java.util.List;

import com.arabook.arabook.member.entity.Member;

public interface MemberSubCategorySelectionService {
  void selectSubCategories(Member member, List<Long> categoryId);
}
