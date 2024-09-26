package com.arabook.arabook.api.review.controller.dto.response;

import java.time.LocalDate;

public record ReviewDetailResponse(
		long reviewId,
		long bookId,
		LocalDate readStartDate,
		LocalDate readEndDate,
		String coverUrl,
		String title,
		String author,
		String reviewTagIcon,
		String reviewTagComment,
		String reviewTagColor,
		String content) {}
