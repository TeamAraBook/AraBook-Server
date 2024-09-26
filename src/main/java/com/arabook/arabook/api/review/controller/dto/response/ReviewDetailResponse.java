package com.arabook.arabook.api.review.controller.dto.response;

public record ReviewDetailResponse(
		long reviewId,
		long bookId,
		String coverUrl,
		String title,
		String author,
		String reviewTagIcon,
		String reviewTagComment,
		String reviewTagColor,
		String content) {}
