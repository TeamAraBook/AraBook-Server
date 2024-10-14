package com.arabook.arabook.review.repository;

import static com.arabook.arabook.common.exception.review.ReviewExceptionType.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.common.exception.review.ReviewException;
import com.arabook.arabook.review.controller.dto.response.ReviewDetailResponse;
import com.arabook.arabook.review.controller.dto.response.ReviewResponse;
import com.arabook.arabook.review.entity.Review;
import com.arabook.arabook.storage.domain.review.entity.QReview;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<ReviewResponse> findReviewsByReviewerId(Long memberId) {
    QReview review = QReview.review;

    List<Review> reviews =
        queryFactory
            .selectFrom(review)
            .where(review.reviewer.memberId.eq(memberId)) // memberId 조건 추가
            .fetch();

    return reviews.stream()
        .map(
            r ->
                ReviewResponse.of(
                    r.getReviewId(),
                    r.getBook().getCoverUrl(),
                    r.getBook().getTitle(),
                    r.getReadStartDate(),
                    r.getReadEndDate(),
                    r.getReviewTag()))
        .toList();
  }

  @Override
  public ReviewDetailResponse findReviewDetailByReviewIdAndReviewerId(
      Long reviewId, Long memberId) {
    QReview review = QReview.review;

    Review foundReview =
        queryFactory
            .selectFrom(review)
            .where(
                review
                    .reviewId
                    .eq(reviewId)
                    .and(review.reviewer.memberId.eq(memberId))) // reviewId, memberId 조건 추가
            .fetchOne();

    if (foundReview == null) {
      throw new ReviewException(NOT_FOUND_REVIEW);
    }

    return ReviewDetailResponse.from(foundReview);
  }
}
