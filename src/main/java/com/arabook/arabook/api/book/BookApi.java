package com.arabook.arabook.api.book;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.book.response.AIRecommendBookResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book", description = "책와 관련된 API")
public interface BookApi {
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "AI 추천 책 조회에 성공했습니다.")})
	@Operation(summary = "홈: AI 추천도서 ", description = "AI 추천도서를 조회합니다.")
	ResponseEntity<ResponseTemplate<AIRecommendBookResponse>> getAIRecommendBook();
}
