package com.arabook.arabook.api.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.review.controller.dto.request.CreateReviewRequest;
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
  public Long createReview(CreateReviewRequest request, Long memberId) {
    Member member = memberRepository.findByMemberIdOrThrow(memberId);
    Book book = bookRepository.findByBookIdOrThrow(request.bookId());
    Review review =
        Review.builder()
            .reviwer(member)
            .book(book)
            .reviewTag(request.reviewTag())
            .readStartDate(request.readStartDate())
            .readEndDate(request.readEndDate())
            .build();
    reviewRepository.save(review);

    return review.getReviewId();
  }
}
