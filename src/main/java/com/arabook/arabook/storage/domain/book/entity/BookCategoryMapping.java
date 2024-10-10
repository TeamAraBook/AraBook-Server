package com.arabook.arabook.storage.domain.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.arabook.arabook.storage.domain.category.entity.SubCategory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "book_category_mappings")
public class BookCategoryMapping {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookCategoryMappingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private SubCategory subCategory;
}
