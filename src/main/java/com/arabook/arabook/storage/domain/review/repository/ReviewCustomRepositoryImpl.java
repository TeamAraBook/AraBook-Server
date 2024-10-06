package com.arabook.arabook.storage.domain.review.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.review.controller.dto.response.ReviewResponse;
import com.arabook.arabook.storage.domain.review.entity.QReview;
import com.arabook.arabook.storage.domain.review.entity.Review;
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
}
