package com.arabook.arabook.api.book.service;

import com.arabook.arabook.api.book.controller.dto.response.BooksResponse;

public interface BookService {
	public BooksResponse getBooksBySearch(String keyword);
}
