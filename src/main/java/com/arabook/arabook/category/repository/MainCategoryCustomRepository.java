package com.arabook.arabook.category.repository;

import java.util.List;

import com.arabook.arabook.category.controller.dto.response.MainCategoryResponse;

public interface MainCategoryCustomRepository {
  List<MainCategoryResponse> findAllOrderByMainCategoryNameAsc();
}
