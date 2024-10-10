package com.arabook.arabook.api.category.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SubCategoryResponse", description = "소분류 카테고리 응답 DTO")
public record SubCategoryResponse(
    @Schema(description = "소분류 카테고리 id", example = "1") long subCategoryId,
    @Schema(description = "소분류 카테고리명", example = "로맨스") String subCategoryName) {}
