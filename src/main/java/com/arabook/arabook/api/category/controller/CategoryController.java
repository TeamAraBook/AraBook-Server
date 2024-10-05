package com.arabook.arabook.api.category.controller;

import static com.arabook.arabook.common.success.category.CategorySuccessType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.category.controller.dto.response.CategoriesResponse;
import com.arabook.arabook.api.category.service.CategoryService;
import com.arabook.arabook.common.response.ResponseTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryApi {
  private final CategoryService categoryService;

  @Override
  @GetMapping
  public ResponseEntity<ResponseTemplate<CategoriesResponse>> getCategories() {
    CategoriesResponse response = categoryService.getCategories();
    return ResponseEntity.ok(ResponseTemplate.success(GET_CATEGORIES, response));
  }
}
