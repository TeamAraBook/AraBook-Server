package com.arabook.arabook.storage.domain.category.repository;

import java.util.List;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;

public interface MainCategoryCustomRepository {
  List<MainCategoryResponse> findAllOrderByMainCategoryNameAsc();
}
