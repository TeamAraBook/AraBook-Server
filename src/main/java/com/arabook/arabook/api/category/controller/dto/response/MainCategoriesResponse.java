package com.arabook.arabook.api.category.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MainCategoriesResponse", description = "대분류 카테고리 리스트 응답 DTO")
public record MainCategoriesResponse(
    @Schema(description = "조회된 대분류 카테고리의 수", example = "1") int totalCount,
    @Schema(
            description = "대분류 카테고리 목록",
            example =
                "[{ \"mainCategoryId\": 1, \"mainCategoryName\": \"로맨스\" }, { \"mainCategoryId\": 2, \"mainCategoryName\": \"판타지\" }]")
        List<MainCategoryResponse> categories) {
  public static MainCategoriesResponse of(int totalCount, List<MainCategoryResponse> categories) {
    return new MainCategoriesResponse(totalCount, categories);
  }
}
