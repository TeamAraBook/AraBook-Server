package com.arabook.arabook.storage.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {}
