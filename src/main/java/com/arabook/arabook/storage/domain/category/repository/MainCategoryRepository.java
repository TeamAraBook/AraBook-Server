package com.arabook.arabook.storage.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.storage.domain.category.entity.MainCategory;

public interface MainCategoryRepository
    extends JpaRepository<MainCategory, Long>, MainCategoryCustomRepository {}
