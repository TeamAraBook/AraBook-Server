package com.arabook.arabook.api.book.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BookResponse", description = "책 응답 DTO")
public record BookResponse(
		@Schema(description = "책 id", example = "1") long bookId,
		@Schema(description = "책 표지 url", example = "https://images/1") String coverUrl,
		@Schema(description = "책 제목", example = "퀸의 대각선") String title,
		@Schema(description = "책 작가", example = "베르나르베르베르") String author) {}
