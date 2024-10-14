package com.arabook.arabook.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.member.entity.MemberSubCategorySelection;

public interface MemberSubCategorySelectionRepository
    extends JpaRepository<MemberSubCategorySelection, Long> {
  void deleteAllByMember(Member member);
}
