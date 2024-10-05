package com.arabook.arabook.storage.domain.member.repository;

import static com.arabook.arabook.common.exception.member.MemberExceptionType.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.common.exception.member.MemberException;
import com.arabook.arabook.storage.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Optional<Member> findByMemberId(final Long memberId);

  default Member findByMemberIdOrThrow(final Long memberId) {
    return findByMemberId(memberId).orElseThrow(() -> new MemberException(NOT_FOUND_MEMBER));
  }
}
