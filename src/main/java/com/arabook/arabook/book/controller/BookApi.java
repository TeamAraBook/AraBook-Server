package com.arabook.arabook.book.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book", description = "책와 관련된 API")
public interface BookApi {
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "책 상세보기 조회에 성공했습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{\"code\": 200, \"message\": \"책 상세 정보를 조회하였습니다\", \"data\": { \"bookId\": 1, \"coverUrl\": \"https://images/1\", \"title\": \"사랑의 마법사는 외로워\", \"author\": \"김하늘\", \"publisher\": \"출판사 A\", \"publicationYear\": \"2022\", \"description\": \"사랑과 마법이 얽힌 판타지 소설입니다.\", \"categories\": [{ \"categoryId\": 1, \"name\": \"판타지\" }, { \"categoryId\": 2, \"name\": \"로맨스\" }], \"hashtags\": [{ \"hashTagId\": 1, \"name\": \"사랑\" }, { \"hashTagId\": 2, \"name\": \"마법\" }] }}"))),
        @ApiResponse(
            responseCode = "404",
            description = "해당 책을 찾을 수 없습니다.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            example =
                                "{ \"code\": 404, \"message\": \"책을 찾을 수 없습니다.\", \"data\": \"No Data\" }")))
      })
  @Operation(summary = "책 상세보기: 책 정보 조회", description = "책 정보를 조회합니다.")
  ResponseEntity<ResponseTemplate<BookDetailResponse>> getBookDetail(Long bookId);

  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "검색에 성공했습니다.")})
  @Operation(
      summary = "책 검색: 책 이름 또는 isbn 또는 저자 이름으로 조회",
      description = "책 이름, isbn, 저자명 중 한가지로 책을 검색합니다.")
  ResponseEntity<ResponseTemplate<BooksResponse>> getBooksBySearch(
      @Parameter(
              description = "검색할 책 이름, ISBN 또는 저자 이름 사용자가 입력값 없이 검색을 누를 경우 빈 문자열로 요청해야 함",
              required = true)
          String keyword);
}
