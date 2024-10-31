package com.arabook.arabook.review.repository;

import static com.arabook.arabook.common.exception.review.ReviewExceptionType.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.*;

import com.arabook.arabook.common.exception.review.ReviewException;
import com.arabook.arabook.member.entity.*;
import com.arabook.arabook.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
  @Query(
      "SELECT r FROM Review r WHERE r.reviewId = :reviewId AND r.reviewer.memberId = :reviewerId")
  Optional<Review> findByReviewIdAndReviewerId(
      @Param("reviewId") final Long reviewId, @Param("reviewerId") final Long reviewerId);

  default Review findByReviewIdAndReviewerIdOrThrow(final Long reviewId, final Long reviewerId) {
    return findByReviewIdAndReviewerId(reviewId, reviewerId)
        .orElseThrow(() -> new ReviewException(NOT_FOUND_REVIEW));
  }

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query("delete from Review r where r.reviewer = :reviewer")
  void deleteByReviewer(@Param("reviewer") Member reviewer);
}
