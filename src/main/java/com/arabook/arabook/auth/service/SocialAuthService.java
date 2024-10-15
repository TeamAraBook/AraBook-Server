package com.arabook.arabook.auth.service;

import com.arabook.arabook.auth.service.vo.AuthMemberVO;

public interface SocialAuthService {
  AuthMemberVO getUserInfo(String accessToken);
}
