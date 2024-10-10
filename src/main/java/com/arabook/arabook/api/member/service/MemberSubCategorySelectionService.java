package com.arabook.arabook.api.member.service;

import java.util.List;

import com.arabook.arabook.storage.domain.member.entity.Member;

public interface MemberSubCategorySelectionService {
  void selectSubCategories(Member member, List<Long> categoryId);
}
