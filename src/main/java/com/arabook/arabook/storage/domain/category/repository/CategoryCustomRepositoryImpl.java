package com.arabook.arabook.storage.domain.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.category.controller.dto.response.CategoryResponse;
import com.arabook.arabook.storage.domain.category.entity.QCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<CategoryResponse> findAllOrderByCategoryNameAsc() {
    QCategory category = QCategory.category;

    return queryFactory
        .select(Projections.fields(CategoryResponse.class, category.categoryId, category.name))
        .from(category)
        .orderBy(category.name.asc())
        .fetch();
  }
}
