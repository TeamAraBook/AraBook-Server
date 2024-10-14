package com.arabook.arabook.category.service;

import java.util.List;

import com.arabook.arabook.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;

public interface CategoryService {
  List<MainCategoryResponse> getMainCategories();

  List<MainCategoryWithSubCategoriesResponse> getSubCategoriesByMainCategories(
      List<Long> mainCategoryIds);
}
