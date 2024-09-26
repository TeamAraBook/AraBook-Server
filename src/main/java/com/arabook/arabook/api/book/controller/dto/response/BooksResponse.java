package com.arabook.arabook.api.book.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BooksResponse", description = "책 리스트 응답 DTO")
public record BooksResponse(
		@Schema(description = "조회된 책의 수", example = "1") int totalCount,
		@Schema(description = "책 목록", example = "1") List<BookResponse> books) {}
