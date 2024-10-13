package com.arabook.arabook.api.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.api.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;
import com.arabook.arabook.storage.domain.category.repository.MainCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
  private final MainCategoryRepository mainCategoryRepository;

  @Override
  public List<MainCategoryResponse> getMainCategories() {
    return mainCategoryRepository.findAllOrderByMainCategoryNameAsc();
  }

  public List<MainCategoryWithSubCategoriesResponse> getSubCategoriesByMainCategories(
      List<Long> mainCategoryIds) {
    return null;
  }
}
