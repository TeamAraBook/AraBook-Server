package com.arabook.arabook.storage.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.entity.MemberSubCategorySelection;

public interface MemberSubCategorySelectionRepository
    extends JpaRepository<MemberSubCategorySelection, Long> {
  void deleteAllByMember(Member member);
}
