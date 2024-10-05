package com.arabook.arabook.storage.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.entity.MemberCategorySelection;

public interface MemberCategorySelectionRepository
    extends JpaRepository<MemberCategorySelection, Long> {
  void deleteAllByMember(Member member);
}
