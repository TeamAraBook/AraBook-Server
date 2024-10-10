package com.arabook.arabook.api.category.controller;

import static com.arabook.arabook.common.success.category.CategorySuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoriesResponse;
import com.arabook.arabook.api.category.service.CategoryService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;

  @Override
  @GetMapping("/main")
  public ResponseEntity<ResponseTemplate<MainCategoriesResponse>> getMainCategories() {
    MainCategoriesResponse response = categoryService.getMainCategories();
    return ResponseEntity.ok(ResponseTemplate.success(GET_MAIN_CATEGORIES, response));
  }
}
