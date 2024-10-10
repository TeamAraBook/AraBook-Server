package com.arabook.arabook.storage.domain.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "sub_categories")
public class SubCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long subCategoryId;

  @ManyToOne
  @JoinColumn(name = "main_category_id")
  private MainCategory mainCategory;

  @NotNull private String subCategoryName;
}
