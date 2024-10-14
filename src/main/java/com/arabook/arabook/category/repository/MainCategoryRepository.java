package com.arabook.arabook.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.category.entity.MainCategory;

public interface MainCategoryRepository
    extends JpaRepository<MainCategory, Long>, MainCategoryCustomRepository {}
