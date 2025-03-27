# 📚 AraBook

## 📒 Backend Developer
<div align="center">
  <table>
    <thead>
      <tr>
        <th colspan="2">김성은 @sung-silver</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>역할</th>
        <td>
          - CI/CD 구축<br>
          - 인증 관련 API: 소셜 로그인, 회원 탈퇴<br>
          - 책 카테고리 관련 API: 온보딩 대분류 카테고리 조회, 온보딩 소분류 카테고리 조회<br>
          - 책 기록 관련 API: 책 기록 목록 조회, 책 기록 수정, 책 기록 생성, 책 기록 조회, 책 기록 삭제<br>
          - 추천 도서 API: 이 달의 베스트 셀러 조회, AI 추천 도서<br>
          - 책 조회 API: 책 상세보기, 책 검색<br>
        </td>
      </tr>
    </tbody>
  </table>
</div>
<br><br>

## 🔑 Key Features
![3  주요 기능 1](https://github.com/user-attachments/assets/5ef05a21-0bfc-4ca7-b1a3-f5b953332c57)
<br>
![3  주요기능 2](https://github.com/user-attachments/assets/291006c3-17f9-4f6b-936a-9a4bcb9ba379)
<br>
![3  주요기능 3](https://github.com/user-attachments/assets/7fcf33ff-a6e5-433f-8dd0-1d0733bd5b91)

<br><br>

## 💻 Development Tech
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<br> 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/amazonrds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
<br>
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<br>
<img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white">
<br>
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<br>

<br><br>

## 📚 ERD
![AraBook ERD](https://github.com/user-attachments/assets/a8fcd2e3-a49d-4dfc-a056-c2b0b5700020)

## 🛠️ System Architecture
![시스템 구성도](https://github.com/user-attachments/assets/952617f8-2cbf-4fcb-972b-f622e2772596)



<br><br>
## 📂 Project Structure
```
📦 AraBook-Server
├─ .github
│  ├─ ISSUE_TEMPLATE
│  │  └─ feature_request.md
│  ├─ pull_request_template.md
│  ├─ release-drafter-config.yml
│  ├─ script
│  │  └─ pre-commit
│  └─ workflows
│     ├─ dev-CI.yml
│     ├─ dev-CICD.yml
│     ├─ prod-CI.yml
│     ├─ prod-CICD.yml
│     └─ release-drafter.yml
├─ .gitignore
├─ Dockerfile
├─ HELP.md
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ arabook
   │  │        └─ arabook
   │  │           ├─ ArabookApplication.java
   │  │           ├─ auth
   │  │           │  ├─ controller
   │  │           │  │  ├─ AuthApi.java
   │  │           │  │  ├─ AuthController.java
   │  │           │  │  ├─ TokenController.java
   │  │           │  │  └─ dto
   │  │           │  │     ├─ request
   │  │           │  │     │  ├─ AuthRequest.java
   │  │           │  │     │  └─ IssueTokenRequest.java
   │  │           │  │     └─ response
   │  │           │  │        ├─ AuthResponse.java
   │  │           │  │        └─ IssueTokenResponse.java
   │  │           │  └─ service
   │  │           │     ├─ AuthService.java
   │  │           │     ├─ AuthServiceImpl.java
   │  │           │     ├─ AuthServiceProvider.java
   │  │           │     ├─ SocialAuthService.java
   │  │           │     └─ vo
   │  │           │        └─ AuthMemberVO.java
   │  │           ├─ book
   │  │           │  ├─ controller
   │  │           │  │  ├─ BookApi.java
   │  │           │  │  ├─ BookController.java
   │  │           │  │  ├─ RecommendApi.java
   │  │           │  │  ├─ RecommendController.java
   │  │           │  │  └─ dto
   │  │           │  │     └─ response
   │  │           │  │        ├─ AIRecommendBookResponse.java
   │  │           │  │        ├─ BookDetailResponse.java
   │  │           │  │        ├─ BookResponse.java
   │  │           │  │        └─ BooksResponse.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BestSeller.java
   │  │           │  │  ├─ Book.java
   │  │           │  │  ├─ BookHashtagMapping.java
   │  │           │  │  └─ BookSubCategoryMapping.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ BookCustomRepository.java
   │  │           │  │  ├─ BookCustomRepositoryImpl.java
   │  │           │  │  └─ BookRepository.java
   │  │           │  └─ service
   │  │           │     ├─ BestSellerService.java
   │  │           │     ├─ BestSellerServiceImpl.java
   │  │           │     ├─ BookService.java
   │  │           │     └─ BookServiceImpl.java
   │  │           ├─ category
   │  │           │  ├─ controller
   │  │           │  │  ├─ CategoryApi.java
   │  │           │  │  ├─ CategoryController.java
   │  │           │  │  └─ dto
   │  │           │  │     └─ response
   │  │           │  │        ├─ MainCategoryResponse.java
   │  │           │  │        ├─ MainCategoryWithSubCategoriesResponse.java
   │  │           │  │        └─ SubCategoryResponse.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ MainCategory.java
   │  │           │  │  └─ SubCategory.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ MainCategoryCustomRepository.java
   │  │           │  │  ├─ MainCategoryCustomRepositoryImpl.java
   │  │           │  │  ├─ MainCategoryRepository.java
   │  │           │  │  ├─ SubCategoryCustomRepository.java
   │  │           │  │  ├─ SubCategoryCustomRepositoryImpl.java
   │  │           │  │  └─ SubCategoryRepository.java
   │  │           │  └─ service
   │  │           │     ├─ CategoryService.java
   │  │           │     └─ CategoryServiceImpl.java
   │  │           ├─ common
   │  │           │  ├─ config
   │  │           │  │  ├─ QuerydslConfig.java
   │  │           │  │  ├─ SecurityConfig.java
   │  │           │  │  ├─ SwaggerConfig.java
   │  │           │  │  └─ WebConfig.java
   │  │           │  ├─ entity
   │  │           │  │  └─ BaseTimeEntity.java
   │  │           │  ├─ exception
   │  │           │  │  ├─ auth
   │  │           │  │  │  ├─ AuthException.java
   │  │           │  │  │  └─ AuthExceptionType.java
   │  │           │  │  ├─ book
   │  │           │  │  │  ├─ BookException.java
   │  │           │  │  │  └─ BookExceptionType.java
   │  │           │  │  ├─ category
   │  │           │  │  │  ├─ CategoryException.java
   │  │           │  │  │  └─ CategoryExceptionType.java
   │  │           │  │  ├─ common
   │  │           │  │  │  ├─ BusinessException.java
   │  │           │  │  │  ├─ ClientException.java
   │  │           │  │  │  ├─ CommonExceptionType.java
   │  │           │  │  │  └─ ExceptionType.java
   │  │           │  │  ├─ member
   │  │           │  │  │  ├─ MemberException.java
   │  │           │  │  │  └─ MemberExceptionType.java
   │  │           │  │  └─ review
   │  │           │  │     ├─ ReviewException.java
   │  │           │  │     └─ ReviewExceptionType.java
   │  │           │  ├─ handler
   │  │           │  │  └─ GlobalExceptionHandler.java
   │  │           │  ├─ redis
   │  │           │  │  └─ repository
   │  │           │  │     ├─ RedisTokenRepository.java
   │  │           │  │     └─ dto
   │  │           │  │        └─ RefreshTokenDTO.java
   │  │           │  ├─ response
   │  │           │  │  └─ ResponseTemplate.java
   │  │           │  ├─ security
   │  │           │  │  ├─ AuthMember.java
   │  │           │  │  ├─ AuthMemberArgumentResolver.java
   │  │           │  │  ├─ MemberAuthentication.java
   │  │           │  │  └─ filter
   │  │           │  │     ├─ JwtAuthenticationFilter.java
   │  │           │  │     └─ JwtExceptionFilter.java
   │  │           │  └─ success
   │  │           │     ├─ book
   │  │           │     │  └─ BookSuccessType.java
   │  │           │     ├─ category
   │  │           │     │  └─ CategorySuccessType.java
   │  │           │     ├─ common
   │  │           │     │  └─ SuccessType.java
   │  │           │     ├─ member
   │  │           │     │  └─ MemberSuccessType.java
   │  │           │     └─ review
   │  │           │        └─ ReviewSuccessType.java
   │  │           ├─ external
   │  │           │  ├─ jwt
   │  │           │  │  ├─ provider
   │  │           │  │  │  ├─ JwtTokenProvider.java
   │  │           │  │  │  └─ JwtTokenProviderImpl.java
   │  │           │  │  ├─ service
   │  │           │  │  │  ├─ JwtService.java
   │  │           │  │  │  └─ JwtServiceImpl.java
   │  │           │  │  └─ validator
   │  │           │  │     ├─ JwtTokenValidator.java
   │  │           │  │     └─ JwtTokenValidatorImpl.java
   │  │           │  └─ social
   │  │           │     └─ service
   │  │           │        ├─ KakaoSocialAuthService.java
   │  │           │        └─ dto
   │  │           │           └─ KakaoUserInfoResponse.java
   │  │           ├─ hashtag
   │  │           │  ├─ controller
   │  │           │  │  └─ dto
   │  │           │  │     └─ response
   │  │           │  │        └─ HashTagResponse.java
   │  │           │  └─ entity
   │  │           │     └─ Hashtag.java
   │  │           ├─ member
   │  │           │  ├─ controller
   │  │           │  │  ├─ MemberApi.java
   │  │           │  │  ├─ MemberController.java
   │  │           │  │  └─ dto
   │  │           │  │     └─ request
   │  │           │  │        └─ MemberOnboardingRequest.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ AIRecommendation.java
   │  │           │  │  ├─ Member.java
   │  │           │  │  ├─ MemberSubCategorySelection.java
   │  │           │  │  └─ enums
   │  │           │  │     ├─ Gender.java
   │  │           │  │     ├─ Role.java
   │  │           │  │     └─ SocialPlatformType.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ AIRecommendationRepository.java
   │  │           │  │  ├─ MemberRepository.java
   │  │           │  │  └─ MemberSubCategorySelectionRepository.java
   │  │           │  └─ service
   │  │           │     ├─ MemberService.java
   │  │           │     ├─ MemberServiceImpl.java
   │  │           │     ├─ MemberSubCategorySelectionService.java
   │  │           │     └─ MemberSubCategorySelectionServiceImpl.java
   │  │           └─ review
   │  │              ├─ controller
   │  │              │  ├─ ReviewApi.java
   │  │              │  ├─ ReviewController.java
   │  │              │  └─ dto
   │  │              │     ├─ request
   │  │              │     │  ├─ CreateReviewRequest.java
   │  │              │     │  └─ UpdateReviewRequest.java
   │  │              │     └─ response
   │  │              │        ├─ ReviewDetailResponse.java
   │  │              │        ├─ ReviewIdResponse.java
   │  │              │        ├─ ReviewResponse.java
   │  │              │        └─ ReviewsResponse.java
   │  │              ├─ entity
   │  │              │  ├─ Review.java
   │  │              │  └─ enums
   │  │              │     └─ ReviewTag.java
   │  │              ├─ repository
   │  │              │  ├─ ReviewCustomRepository.java
   │  │              │  ├─ ReviewCustomRepositoryImpl.java
   │  │              │  └─ ReviewRepository.java
   │  │              └─ service
   │  │                 ├─ ReviewService.java
   │  │                 └─ ReviewServiceImpl.java
   │  └─ resources
   │     ├─ application-dev.yml
   │     ├─ application-local.yml
   │     ├─ application-prod.yml
   │     ├─ application-test.yml
   │     └─ images
   │        └─ card
   │           ├─ AVERAGE.png
   │           ├─ DISAPPOINTED.png
   │           ├─ ENJOYABLE.png
   │           ├─ LIFE_CHANGING.png
   │           └─ SLIGHTLY_DISAPPOINTED.png
   └─ test
      └─ java
         └─ com
            └─ arabook
               └─ arabook
                  └─ ArabookApplicationTests.java
```
