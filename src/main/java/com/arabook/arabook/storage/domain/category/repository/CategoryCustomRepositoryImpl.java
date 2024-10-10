package com.arabook.arabook.storage.domain.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.category.controller.dto.response.CategoryResponse;
import com.arabook.arabook.storage.domain.category.entity.QSubCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<CategoryResponse> findAllOrderBySubCategoryNameAsc() {
    QSubCategory subCategory = QSubCategory.subCategory;

    return queryFactory
        .select(
            Projections.constructor(
                CategoryResponse.class, subCategory.subCategoryId, subCategory.subCategoryName))
        .from(subCategory)
        .orderBy(subCategory.subCategoryName.asc())
        .fetch();
  }
}
