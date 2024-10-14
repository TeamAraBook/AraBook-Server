package com.arabook.arabook.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.arabook.arabook.storage.domain.category.entity.QMainCategory;
import com.arabook.arabook.storage.domain.category.entity.QSubCategory;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SubCategoryCustomRepositoryImpl implements SubCategoryCustomRepository {
  private final JPAQueryFactory queryFactory;

  public List<Tuple> findSubCategoriesByMainCategories(List<Long> mainCategoryIds) {
    QMainCategory mainCategory = QMainCategory.mainCategory;
    QSubCategory subCategory = QSubCategory.subCategory;

    return queryFactory
        .select(
            mainCategory.mainCategoryId,
            mainCategory.mainCategoryName,
            subCategory.subCategoryId,
            subCategory.subCategoryName)
        .from(mainCategory)
        .leftJoin(subCategory)
        .on(subCategory.mainCategory.mainCategoryId.eq(mainCategory.mainCategoryId))
        .where(mainCategory.mainCategoryId.in(mainCategoryIds))
        .orderBy(mainCategory.mainCategoryName.asc(), subCategory.subCategoryName.asc())
        .fetch();
  }
}
