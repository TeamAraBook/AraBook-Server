package com.arabook.arabook.category.repository;

import java.util.List;

import com.querydsl.core.Tuple;

public interface SubCategoryCustomRepository {
  List<Tuple> findSubCategoriesByMainCategories(List<Long> mainCategoryIds);
}
