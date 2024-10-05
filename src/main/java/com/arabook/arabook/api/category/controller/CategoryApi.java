package com.arabook.arabook.api.category.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.category.controller.dto.response.CategoriesResponse;

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
            description = "카테고리 조회에 성공했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 200, \"message\": \"카테고리 조회에 성공했습니다\", \"data\": { \"totalCount\": 2, \"categories\": \"[{ \"categoryId\": 1, \"categoryName\": \"로맨스\" }, { \"categoryId\": 2, \"categoryName\": \"판타지\" }]\"}")))
      })
  @Operation(summary = "온보딩: 카테고리 리스트 조회", description = "카테고리를 조회합니다.")
  ResponseEntity<CategoriesResponse> getCategories();
}
