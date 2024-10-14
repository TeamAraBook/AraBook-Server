package com.arabook.arabook.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "monthly_best_sellers")
public class BestSeller {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bestSellerId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", unique = true)
  private Book book;

  private int bookRank;
}
