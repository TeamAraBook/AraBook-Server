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
                                "{\"code\":200,\"message\":\"대분류 카테고리 조회에 성공했습니다\",\"data\":[{\"mainCategoryId\":5,\"mainCategoryName\":\"SF\"},{\"mainCategoryId\":7,\"mainCategoryName\":\"공포소설\"},{\"mainCategoryId\":2,\"mainCategoryName\":\"로맨스\"},{\"mainCategoryId\":3,\"mainCategoryName\":\"시대극\"},{\"mainCategoryId\":6,\"mainCategoryName\":\"역사소설\"},{\"mainCategoryId\":8,\"mainCategoryName\":\"청소년소설\"},{\"mainCategoryId\":4,\"mainCategoryName\":\"추리소설\"},{\"mainCategoryId\":1,\"mainCategoryName\":\"판타지\"},{\"mainCategoryId\":9,\"mainCategoryName\":\"패러디\"},{\"mainCategoryId\":10,\"mainCategoryName\":\"희곡\"}]}")))
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
                                "{\"code\":200,\"message\":\"대분류 카테고리별 소분류 카테고리 리스트 조회에 성공했습니다\",\"data\":[{\"mainCategoryId\":2,\"mainCategoryName\":\"로맨스\",\"subCategories\":[{\"subCategoryId\":3,\"subCategoryName\":\"로맨스소설\"},{\"subCategoryId\":4,\"subCategoryName\":\"로맨스판타지\"}]},{\"mainCategoryId\":1,\"mainCategoryName\":\"판타지\",\"subCategories\":[{\"subCategoryId\":2,\"subCategoryName\":\"판타지로맨스\"},{\"subCategoryId\":1,\"subCategoryName\":\"판타지소설\"}]}]}")))
      })
  @Operation(summary = "온보딩: 대분류 카테고리 리스트 조회", description = "책 대분류 카테고리를 조회합니다.")
  ResponseEntity<ResponseTemplate<List<MainCategoryWithSubCategoriesResponse>>>
      getSubCategoriesByMainCategories(List<Long> mainCategoryIds);
}
