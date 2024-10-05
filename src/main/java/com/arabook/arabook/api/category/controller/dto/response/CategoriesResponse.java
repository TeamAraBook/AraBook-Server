package com.arabook.arabook.api.category.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CategoriesResponse", description = "카테고리 리스트 응답 DTO")
public record CategoriesResponse(
    @Schema(description = "조회된 카테고리의 수", example = "1") int totalCount,
    @Schema(
            description = "카테고리 목록",
            example =
                "[{ \"categoryId\": 1, \"categoryName\": \"로맨스\" }, { \"categoryId\": 2, \"categoryName\": \"판타지\" }]")
        List<CategoryResponse> categories) {
  public static CategoriesResponse of(int totalCount, List<CategoryResponse> categories) {
    return new CategoriesResponse(totalCount, categories);
  }
}
