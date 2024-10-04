package com.arabook.arabook.storage.redis.repository.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 604800016)
public class RefreshTokenDTO {
  @Id @Indexed private String memberId;

  private String refreshToken;

  public void updateRefreshToken(final String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @Builder
  private RefreshTokenDTO(final String memberId, final String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }
}
