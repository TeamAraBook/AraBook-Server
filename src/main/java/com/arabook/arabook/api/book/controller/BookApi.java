package com.arabook.arabook.api.book.controller;

import org.springframework.http.ResponseEntity;

import com.arabook.arabook.api.book.controller.dto.response.BookDetailResponse;
import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;
import com.arabook.arabook.common.response.ResponseTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "book", description = "책와 관련된 API")
public interface BookApi {
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "책 상세보기 조회에 성공했습니다."),
        @ApiResponse(responseCode = "404", description = "해당 책을 찾을 수 없습니다.")
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
          String search);
}
