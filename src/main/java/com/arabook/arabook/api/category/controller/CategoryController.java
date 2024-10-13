package com.arabook.arabook.api.category.controller;

import static com.arabook.arabook.common.success.category.CategorySuccessType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.api.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;
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
  public ResponseEntity<ResponseTemplate<List<MainCategoryResponse>>> getMainCategories() {
    List<MainCategoryResponse> response = categoryService.getMainCategories();
    return ResponseEntity.ok(ResponseTemplate.success(GET_MAIN_CATEGORIES, response));
  }

  @Override
  @GetMapping("/sub")
  public ResponseEntity<ResponseTemplate<List<MainCategoryWithSubCategoriesResponse>>>
      getSubCategoriesByMainCategories(@RequestParam("mainIds") List<Long> mainIds) {
    List<MainCategoryWithSubCategoriesResponse> response =
        categoryService.getSubCategoriesByMainCategories(mainIds);
    return ResponseEntity.ok(
        ResponseTemplate.success(GET_SUB_CATEGORIES_BY_MAIN_CATEGORIES, response));
  }
}
