package com.arabook.arabook.api.category.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.category.controller.dto.response.MainCategoryResponse;
import com.arabook.arabook.api.category.controller.dto.response.MainCategoryWithSubCategoriesResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "category", description = "책의 카테고리와 관련된 API")
public interface CategoryApi {
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "대분류 카테고리 조회에 성공했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 200, \"message\": \"대분류 카테고리 조회에 성공했습니다\", \"data\": { [ { \"mainCategoryId\": 1, \"mainCategoryName\": \"로맨스\" }, { \"mainCategoryId\": 2, \"mainCategoryName\": \"판타지\" } ] }")))
      })
  @Operation(summary = "온보딩: 대분류 카테고리 리스트 조회", description = "책 대분류 카테고리를 조회합니다.")
  ResponseEntity<ResponseTemplate<List<MainCategoryResponse>>> getMainCategories();

  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "대분류 카테고리별 소분류 카테고리 리스트 조회에 성공했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 200, \"message\": \"대분류 카테고리별 소분류 카테고리 리스트 조회에 성공했습니다\", \"data\": { \"totalCount\": 2, \"mainCategories\": [ { \"mainCategoryId\": 1, \"mainCategoryName\": \"로맨스\" }, { \"mainCategoryId\": 2, \"mainCategoryName\": \"판타지\" }]}}")))
      })
  @Operation(summary = "온보딩: 대분류 카테고리 리스트 조회", description = "책 대분류 카테고리를 조회합니다.")
  ResponseEntity<ResponseTemplate<List<MainCategoryWithSubCategoriesResponse>>>
      getSubCategoriesByMainCategories(List<Long> mainCategoryIds);
}
