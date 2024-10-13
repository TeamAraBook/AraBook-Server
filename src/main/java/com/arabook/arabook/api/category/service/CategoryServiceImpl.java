package com.arabook.arabook.api.category.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.api.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;
import com.arabook.arabook.api.category.controller.dto.response.SubCategoryResponse;
import com.arabook.arabook.storage.domain.category.repository.MainCategoryRepository;
import com.arabook.arabook.storage.domain.category.repository.SubCategoryRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
  private static final int MAIN_CATEGORY_ID_INDEX = 0;
  private static final int MAIN_CATEGORY_NAME_INDEX = 1;
  private static final int SUB_CATEGORY_ID_INDEX = 2;
  private static final int SUB_CATEGORY_NAME_INDEX = 3;

  private final MainCategoryRepository mainCategoryRepository;
  private final SubCategoryRepository subCategoryRepository;

  @Override
  public List<MainCategoryResponse> getMainCategories() {
    return mainCategoryRepository.findAllOrderByMainCategoryNameAsc();
  }

  @Override
  public List<MainCategoryWithSubCategoriesResponse> getSubCategoriesByMainCategories(
      final List<Long> mainCategoryIds) {
    final List<Tuple> findSubCategoriesByMainCategories =
        subCategoryRepository.findSubCategoriesByMainCategories(mainCategoryIds);
    return subCategoriesGroupByMainCategory(findSubCategoriesByMainCategories);
  }

  private List<MainCategoryWithSubCategoriesResponse> subCategoriesGroupByMainCategory(
      final List<Tuple> findSubCategoriesByMainCategories) {
    Map<Long, MainCategoryWithSubCategoriesResponse> resultMap = new LinkedHashMap<>();

    for (Tuple tuple : findSubCategoriesByMainCategories) {
      Long mainCategoryId = tuple.get(MAIN_CATEGORY_ID_INDEX, Long.class);
      String mainCategoryName = tuple.get(MAIN_CATEGORY_NAME_INDEX, String.class);
      Long subCategoryId = tuple.get(SUB_CATEGORY_ID_INDEX, Long.class);
      String subCategoryName = tuple.get(SUB_CATEGORY_NAME_INDEX, String.class);

      boolean validSubCategory = subCategoryId != null && subCategoryName != null;

      if (validSubCategory) {
        MainCategoryWithSubCategoriesResponse response =
            resultMap.computeIfAbsent(
                mainCategoryId,
                id ->
                    new MainCategoryWithSubCategoriesResponse(
                        mainCategoryId, mainCategoryName, new ArrayList<>()));

        response.subCategories().add(new SubCategoryResponse(subCategoryId, subCategoryName));
      }
    }
    return new ArrayList<>(resultMap.values());
  }
}
