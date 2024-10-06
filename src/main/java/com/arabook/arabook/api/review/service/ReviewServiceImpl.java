package com.arabook.arabook.api.review.service;

import static com.arabook.arabook.storage.domain.member.entity.QMember.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.request.UpdateReviewRequest;
import com.arabook.arabook.api.review.controller.dto.response.ReviewIdResponse;
import com.arabook.arabook.api.review.controller.dto.response.ReviewResponse;
import com.arabook.arabook.api.review.controller.dto.response.ReviewsResponse;
import com.arabook.arabook.storage.domain.book.entity.Book;
import com.arabook.arabook.storage.domain.book.repository.BookRepository;
import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.repository.MemberRepository;
import com.arabook.arabook.storage.domain.review.entity.Review;
import com.arabook.arabook.storage.domain.review.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
  private final MemberRepository memberRepository;
  private final BookRepository bookRepository;
  private final ReviewRepository reviewRepository;

  @Override
  @Transactional
  public ReviewIdResponse createReview(CreateReviewRequest request, Long memberId) {
    Member member = memberRepository.findByMemberIdOrThrow(memberId);
    Book book = bookRepository.findByBookIdOrThrow(request.bookId());
    Review review =
        Review.builder()
            .reviewer(member)
            .book(book)
            .content(request.content())
            .reviewTag(request.reviewTag())
            .readStartDate(request.readStartDate())
            .readEndDate(request.readEndDate())
            .build();
    reviewRepository.save(review);

    return ReviewIdResponse.of(review.getReviewId());
  }

  @Override
  @Transactional
  public ReviewIdResponse updateReview(final UpdateReviewRequest request, final Long memberId) {
    Review review =
        reviewRepository.findByReviewIdAndReviewerIdOrThrow(request.reviewId(), memberId);
    review.updateReview(
        request.content(), request.reviewTag(), request.readStartDate(), request.readEndDate());
    return ReviewIdResponse.of(review.getReviewId());
  }

  @Override
  public ReviewsResponse getReviews(Long memberId) {
    List<ReviewResponse> reviews = reviewRepository.findReviewsByReviewerId(memberId);
    return ReviewsResponse.of(reviews.size(), reviews);
  }
}
