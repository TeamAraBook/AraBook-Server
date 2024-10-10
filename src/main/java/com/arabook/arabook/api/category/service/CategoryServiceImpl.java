package com.arabook.arabook.api.category.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.api.category.controller.dto.response.CategoriesResponse;
import com.arabook.arabook.api.category.controller.dto.response.CategoryResponse;
import com.arabook.arabook.storage.domain.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public CategoriesResponse getCategories() {
    List<CategoryResponse> categories = categoryRepository.findAllOrderBySubCategoryNameAsc();
    return CategoriesResponse.of(categories.size(), categories);
  }
}
