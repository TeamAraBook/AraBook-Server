package com.arabook.arabook.member.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.transaction.annotation.*;

import com.arabook.arabook.member.entity.*;

public interface AIRecommendationRepository extends JpaRepository<AIRecommendation, Long> {
  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("delete from AIRecommendation ar where ar.member = :member")
  void deleteByMember(@Param("member") Member member);
}
