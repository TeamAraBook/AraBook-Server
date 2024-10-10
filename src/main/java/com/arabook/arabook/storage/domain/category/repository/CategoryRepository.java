package com.arabook.arabook.storage.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.category.entity.SubCategory;

public interface CategoryRepository
    extends JpaRepository<SubCategory, Long>, CategoryCustomRepository {}
