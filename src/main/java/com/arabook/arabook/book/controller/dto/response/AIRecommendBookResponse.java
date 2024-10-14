package com.arabook.arabook.book.controller.dto.response;

import java.util.List;

import com.arabook.arabook.api.category.controller.dto.response.SubCategoryResponse;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AIRecommendBookResponse", description = "AI 추천 책 응답 DTO")
public record AIRecommendBookResponse(
    @Schema(description = "책 id", example = "1") long bookId,
    @Schema(description = "책 표지 url", example = "https://images/1") String coverUrl,
    @Schema(description = "책 제목", example = "퀸의 대각선") String title,
    @Schema(description = "책 작가", example = "베르나르베르베르") String author,
    @Schema(description = "책 카테고리") List<SubCategoryResponse> categories) {
  public static AIRecommendBookResponse of(
      long bookId,
      String coverUrl,
      String title,
      String author,
      List<SubCategoryResponse> categories) {
    return new AIRecommendBookResponse(bookId, coverUrl, title, author, categories);
  }
}
