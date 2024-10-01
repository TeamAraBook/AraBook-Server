package com.arabook.arabook.storage.domain.review.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.arabook.arabook.storage.domain.book.entity.Book;
import com.arabook.arabook.storage.domain.common.entity.BaseTimeEntity;
import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.review.entity.enums.ReviewTag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "reviews")
public class Review extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long reviewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id")
	Member reviwer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	Book book;

	ReviewTag reviewTag;

	LocalDate readStartDate;

	LocalDate readEndDate;

	@Builder
	private Review(
			Member reviwer,
			Book book,
			ReviewTag reviewTag,
			LocalDate readStartDate,
			LocalDate readEndDate) {
		this.reviwer = reviwer;
		this.book = book;
		this.reviewTag = reviewTag;
		validateReadDate(readStartDate, readEndDate);
		this.readStartDate = readStartDate;
		this.readEndDate = readEndDate;
	}

	private void validateReadDate(LocalDate readStartDate, LocalDate readEndDate) {
		if (readStartDate.isAfter(readEndDate)) {
			// TODO: 서비스 custom exception으로 변경
			throw new IllegalArgumentException("읽기 시작한 날짜가 다 읽은 날짜보다 늦을 수 없습니다.");
		}
	}
}
