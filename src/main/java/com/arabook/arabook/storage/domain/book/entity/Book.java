package com.arabook.arabook.storage.domain.book.entity;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@NotNull
	@Column(length = 13, unique = true)
	private String isbn;

	@NotNull private String title;

	@NotNull private String author;

	@NotNull private String coverUrl;

	@NotNull private Year publishYear;

	@NotNull private String publisher;

	@NotNull private String description;

	@Builder
	private Book(
			String isbn,
			String title,
			String author,
			String coverUrl,
			Year publishYear,
			String publisher,
			String description) {
		this.isbn = validateIsbn(isbn);
		this.title = title;
		this.author = author;
		this.coverUrl = coverUrl;
		this.publishYear = publishYear;
		this.publisher = publisher;
		this.description = description;
	}

	private String validateIsbn(String isbn) {
		// TODO:: ISBN 유효성 검사에 대한 예외 customException으로 변경
		if (isbn.length() != 13) {
			throw new IllegalArgumentException("ISBN은 13자리여야 합니다.");
		}
		return isbn;
	}
}
