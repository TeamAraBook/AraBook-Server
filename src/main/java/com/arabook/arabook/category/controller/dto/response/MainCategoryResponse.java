package com.arabook.arabook.category.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MainCategoryResponse", description = "대분류 카테고리 응답 DTO")
public record MainCategoryResponse(
    @Schema(description = "대분류 카테고리 id", example = "1") long mainCategoryId,
    @Schema(description = "대분류 카테고리명", example = "로맨스") String mainCategoryName) {}
