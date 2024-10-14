package com.arabook.arabook.book.controller.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "BooksResponse", description = "책 리스트 응답 DTO")
public record BooksResponse(
    @Schema(description = "조회된 책의 수", example = "1") int totalCount,
    @Schema(
            description = "책 목록",
            example =
                "{ \"bookId\": 1, \"coverUrl\": \"https://images/1\", \"title\": \"사랑의 마법사는 외로워\", \"author\": \"김하늘\", \"publisher\": \"출판사 A\", \"publicationYear\": \"2022\", \"description\": \"사랑과 마법이 얽힌 판타지 소설입니다.\", \"categories\": [{ \"categoryId\": 1, \"name\": \"판타지\" }, { \"categoryId\": 2, \"name\": \"로맨스\" }], \"hashtags\": [{ \"hashTagId\": 1, \"name\": \"사랑\" }, { \"hashTagId\": 2, \"name\": \"마법\" }] }")
        List<BookResponse> books) {
  public static BooksResponse of(int totalCount, List<BookResponse> books) {
    return new BooksResponse(totalCount, books);
  }
}
