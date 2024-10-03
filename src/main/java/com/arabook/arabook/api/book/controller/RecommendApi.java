package com.arabook.arabook.api.book.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.book.controller.dto.response.AIRecommendBookResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "RecommendBook", description = "추천 도서 API")
public interface RecommendApi {
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "AI 추천 책 조회에 성공했습니다.")})
  @Operation(summary = "홈: AI 추천도서 ", description = "AI 추천도서를 조회합니다.")
  ResponseEntity<ResponseTemplate<AIRecommendBookResponse>> getAIRecommendBook();

  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "베스트셀러 책 조회에 성공했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\":200,\"message\":\"베스트셀러 목록을 조회하였습니다\",\"data\":{\"totalCount\":5,\"books\":[{\"bookId\":1,\"coverUrl\":\"https://images/1\",\"title\":\"사랑의 마법사는 외로워\",\"author\":\"김하늘\"},{\"bookId\":2,\"coverUrl\":\"https://images/2\",\"title\":\"시간을 달리는 소녀\",\"author\":\"고양이\"},{\"bookId\":3,\"coverUrl\":\"https://images/3\",\"title\":\"나의 소중한 친구\",\"author\":\"이수진\"},{\"bookId\":4,\"coverUrl\":\"https://images/4\",\"title\":\"어둠 속의 빛\",\"author\":\"박지민\"},{\"bookId\":5,\"coverUrl\":\"https://images/5\",\"title\":\"우주를 여행하는 히치하이커\",\"author\":\"최민호\"}]}}")))
      })
  @Operation(summary = "홈: 이달의 베스트셀러 조회", description = "이달의 베스트셀러를 조회합니다.")
  ResponseEntity<ResponseTemplate<BooksResponse>> getBestSellerBooks();
}
