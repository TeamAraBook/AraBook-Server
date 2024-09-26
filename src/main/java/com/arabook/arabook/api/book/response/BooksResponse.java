package com.arabook.arabook.api.book.response;

import java.util.List;

public record BooksResponse(int tatalCount, List<BookResponse> books) {}
