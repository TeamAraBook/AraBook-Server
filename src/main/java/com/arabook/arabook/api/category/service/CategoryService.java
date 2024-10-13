package com.arabook.arabook.api.category.service;

import java.util.List;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.api.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;

public interface CategoryService {
  List<MainCategoryResponse> getMainCategories();

  List<MainCategoryWithSubCategoriesResponse> getSubCategoriesByMainCategories(
      List<Long> mainCategoryIds);
}
