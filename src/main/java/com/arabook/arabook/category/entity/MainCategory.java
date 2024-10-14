package com.arabook.arabook.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "main_categories")
public class MainCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mainCategoryId;

  @NotNull private String mainCategoryName;
}
