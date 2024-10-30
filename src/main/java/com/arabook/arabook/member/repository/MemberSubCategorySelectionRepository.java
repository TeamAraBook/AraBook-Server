package com.arabook.arabook.member.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;

import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.member.entity.MemberSubCategorySelection;

public interface MemberSubCategorySelectionRepository
    extends JpaRepository<MemberSubCategorySelection, Long> {

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("delete from MemberSubCategorySelection mss where mss.member =: member")
  void deleteByMember(@Param("member") Member member);
}
