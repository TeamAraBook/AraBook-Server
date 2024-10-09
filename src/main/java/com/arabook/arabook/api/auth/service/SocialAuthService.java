package com.arabook.arabook.api.auth.service;

import com.arabook.arabook.api.auth.service.vo.AuthMemberVO;

public interface SocialAuthService {
  AuthMemberVO getUserInfo(String accessToken);
}
