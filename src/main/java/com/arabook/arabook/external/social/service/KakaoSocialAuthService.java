package com.arabook.arabook.external.social.service;

import static com.arabook.arabook.common.exception.auth.AuthExceptionType.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.arabook.arabook.api.auth.service.SocialAuthService;
import com.arabook.arabook.api.auth.service.vo.AuthMemberVO;
import com.arabook.arabook.common.exception.auth.AuthException;
import com.arabook.arabook.external.social.service.dto.KakaoUserInfoResponse;
import com.arabook.arabook.member.entity.enums.SocialPlatformType;

import reactor.core.publisher.Mono;

@Service
public class KakaoSocialAuthService implements SocialAuthService {
  private static final String KAKAO_API_USER_URL_HOST = "https://kapi.kakao.com/";
  private static final String KAKAO_API_USER_INFO_PATH = "v2/user/me";
  private static final String KAKAO_AUTHORIZATION_TYPE = "Bearer ";
  private static final String KAKAO_CONTENT_TYPE =
      "application/x-www-form-urlencoded;charset=utf-8";
  private static final SocialPlatformType KAKAO_PLATFORM_TYPE = SocialPlatformType.KAKAO;

  @Override
  public AuthMemberVO getUserInfo(String accessToken) {
    KakaoUserInfoResponse response =
        WebClient.create(KAKAO_API_USER_URL_HOST)
            .get()
            .uri(
                uriBuilder -> uriBuilder.scheme("https").path(KAKAO_API_USER_INFO_PATH).build(true))
            .header(HttpHeaders.AUTHORIZATION, KAKAO_AUTHORIZATION_TYPE + accessToken)
            .header(HttpHeaders.CONTENT_TYPE, KAKAO_CONTENT_TYPE)
            .retrieve()
            .onStatus(
                HttpStatusCode::is4xxClientError,
                clientResponse -> Mono.error(new AuthException(INVALID_REQUEST_SOCIAL_LOGIN)))
            .bodyToMono(KakaoUserInfoResponse.class)
            .block();

    String platformId = response.id().toString();

    return AuthMemberVO.of(platformId, KAKAO_PLATFORM_TYPE);
  }
}
