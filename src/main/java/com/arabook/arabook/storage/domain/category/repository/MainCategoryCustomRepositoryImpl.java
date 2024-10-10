package com.arabook.arabook.storage.domain.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.storage.domain.category.entity.QMainCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MainCategoryCustomRepositoryImpl implements MainCategoryCustomRepository {
  private final JPAQueryFactory queryFactory;

  @Override
  public List<MainCategoryResponse> findAllOrderByMainCategoryNameAsc() {
    QMainCategory mainCategory = QMainCategory.mainCategory;

    return queryFactory
        .select(
            Projections.constructor(
                MainCategoryResponse.class,
                mainCategory.mainCategoryId,
                mainCategory.mainCategoryName))
        .from(mainCategory)
        .orderBy(mainCategory.mainCategoryName.asc())
        .fetch();
  }
}
