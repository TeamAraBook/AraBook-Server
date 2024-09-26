package com.arabook.arabook.api.book.controller.dto.response;

import java.util.List;

public record BooksResponse(int tatalCount, List<BookResponse> books) {}
