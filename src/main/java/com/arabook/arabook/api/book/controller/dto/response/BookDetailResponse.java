package com.arabook.arabook.api.book.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BookDetailResponse", description = "책 상세 조회 응답 DTO")
public record BookDetailResponse(
		@Schema(description = "책 id", example = "1") long bookId,
		@Schema(description = "책 표지 url", example = "https://images/1") String coverUrl,
		@Schema(description = "책 제목", example = "퀸의 대각선") String title,
		@Schema(description = "책 작가", example = "베르나르베르베르") String author,
		@Schema(description = "책 출판사", example = "출판사") String publisher,
		@Schema(description = "책 출판일", example = "2021-01-01") String publicationDate,
		@Schema(description = "책 설명", example = "책 설명") String description,
		@Schema(description = "책 카테고리") List<CategoryResponse> categories,
		@Schema(description = "책 해시태그") List<HashTagResponse> hashtags) {}
