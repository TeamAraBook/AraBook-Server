package com.arabook.arabook.review.entity;

import static com.arabook.arabook.common.exception.review.ReviewExceptionType.*;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import com.arabook.arabook.book.entity.Book;
import com.arabook.arabook.common.exception.review.ReviewException;
import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.review.entity.enums.ReviewTag;
import com.arabook.arabook.storage.domain.common.entity.BaseTimeEntity;

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
  private Long reviewId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reviewer_id")
  private Member reviewer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  private String content;

  @NotNull
  @Enumerated(EnumType.STRING)
  @NotNull
  private ReviewTag reviewTag;

  @NotNull private LocalDate readStartDate;

  @NotNull private LocalDate readEndDate;

  @Builder
  private Review(
      Member reviewer,
      Book book,
      String content,
      ReviewTag reviewTag,
      LocalDate readStartDate,
      LocalDate readEndDate) {
    this.reviewer = reviewer;
    this.book = book;
    this.content = content;
    this.reviewTag = reviewTag;
    validateReadDate(readStartDate, readEndDate);
    this.readStartDate = readStartDate;
    this.readEndDate = readEndDate;
  }

  private void validateReadDate(LocalDate readStartDate, LocalDate readEndDate) {
    if (readStartDate.isAfter(readEndDate)) {
      throw new ReviewException(INVALID_READ_START_DATE);
    }
  }

  public void updateReview(
      String content, ReviewTag reviewTag, LocalDate readStartDate, LocalDate readEndDate) {
    validateReadDate(readStartDate, readEndDate);
    this.content = content;
    this.reviewTag = reviewTag;
    this.readStartDate = readStartDate;
    this.readEndDate = readEndDate;
  }
}
