package com.arabook.arabook.api.book.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CategoryResponse", description = "책 카테고리 응답 DTO")
public record CategoryResponse(
		@Schema(description = "카테고리 id", example = "1") long categoryId,
		@Schema(description = "카테고리 이름", example = "국내소설") String name) {}
