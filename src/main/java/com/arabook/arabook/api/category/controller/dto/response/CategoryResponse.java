package com.arabook.arabook.api.category.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CategoryResponse", description = "카테고리 응답 DTO")
public record CategoryResponse(
    @Schema(description = "카테고리 id", example = "1") long categoryId,
    @Schema(description = "카테고리명", example = "로맨스") String categoryName) {}
