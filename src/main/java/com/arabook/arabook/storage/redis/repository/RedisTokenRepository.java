package com.arabook.arabook.storage.redis.repository;

import static com.arabook.arabook.common.exception.auth.AuthExceptionType.*;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arabook.arabook.common.exception.auth.AuthException;
import com.arabook.arabook.storage.redis.repository.dto.RefreshTokenDTO;

@Repository
public interface RedisTokenRepository extends CrudRepository<RefreshTokenDTO, String> {
  Optional<RefreshTokenDTO> findByMemberId(final String memberId);

  default RefreshTokenDTO findByMemberIdOrElseThrowException(final String memberId) {
    return findByMemberId(memberId).orElseThrow(() -> new AuthException(UNAUTHORIZED_TOKEN));
  }
}
