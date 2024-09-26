package com.arabook.arabook.api.book.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "HashTagResponse", description = "책 해시태그 응답 DTO")
public record HashTagResponse(
		@Schema(description = "해시태그 id", example = "1") long hashTagId,
		@Schema(description = "해시태그 이름", example = "감동") String name) {}
