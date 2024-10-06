package com.arabook.arabook.storage.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {}
