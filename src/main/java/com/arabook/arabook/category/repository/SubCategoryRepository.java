package com.arabook.arabook.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arabook.arabook.category.entity.SubCategory;

public interface SubCategoryRepository
    extends JpaRepository<SubCategory, Long>, SubCategoryCustomRepository {}
