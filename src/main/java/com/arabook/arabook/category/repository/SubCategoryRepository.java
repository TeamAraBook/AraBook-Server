package com.arabook.arabook.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arabook.arabook.category.entity.SubCategory;

public interface SubCategoryRepository
    extends JpaRepository<SubCategory, Long>, SubCategoryCustomRepository {

  @Query("SELECT s FROM SubCategory s WHERE s.subCategoryId IN :categoryIds")
  List<SubCategory> findAllInIds(List<Long> categoryIds);
}
