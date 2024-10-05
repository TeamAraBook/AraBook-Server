package com.arabook.arabook.api.member.service;

import java.util.List;

import com.arabook.arabook.storage.domain.member.entity.Member;

public interface MemberCategorySelectionService {
  void selectCategories(Member member, List<Long> categoryId);
}
