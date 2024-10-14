package com.arabook.arabook.category.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
    name = "MainCategoryWithSubCategoriesResponse",
    description = "대분류 카테고리별 소분류 리스트 카테고리 응답 DTO")
public record MainCategoryWithSubCategoriesResponse(
    @Schema(description = "대분류 카테고리 id", example = "1") long mainCategoryId,
    @Schema(description = "대분류 카테고리 이름", example = "소설") String mainCategoryName,
    @Schema(description = "해당 대분류의 소분류 카테고리 리스트") List<SubCategoryResponse> subCategories) {
  public static MainCategoryWithSubCategoriesResponse of(
      long mainCategoryId, String mainCategoryName, List<SubCategoryResponse> subCategories) {
    return new MainCategoryWithSubCategoriesResponse(
        mainCategoryId, mainCategoryName, subCategories);
  }
}
