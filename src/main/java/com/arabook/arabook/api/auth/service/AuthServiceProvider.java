package com.arabook.arabook.api.auth.service;

import java.util.concurrent.ConcurrentHashMap;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.arabook.arabook.external.social.service.KakaoSocialAuthService;
import com.arabook.arabook.member.entity.enums.SocialPlatformType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthServiceProvider {
  private static final ConcurrentHashMap<SocialPlatformType, SocialAuthService> authServiceMap =
      new ConcurrentHashMap<>();

  private final KakaoSocialAuthService kakaoAuthService;

  @PostConstruct
  void initialAuthServiceMap() {
    authServiceMap.put(SocialPlatformType.KAKAO, kakaoAuthService);
  }

  public SocialAuthService getAuthService(SocialPlatformType platformType) {
    return authServiceMap.get(platformType);
  }
}
