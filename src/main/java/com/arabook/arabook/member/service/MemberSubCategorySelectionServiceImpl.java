package com.arabook.arabook.member.service;

import static com.arabook.arabook.common.exception.category.CategoryExceptionType.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.category.entity.SubCategory;
import com.arabook.arabook.category.repository.SubCategoryRepository;
import com.arabook.arabook.common.exception.category.CategoryException;
import com.arabook.arabook.member.entity.Member;
import com.arabook.arabook.member.entity.MemberSubCategorySelection;
import com.arabook.arabook.member.repository.MemberSubCategorySelectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberSubCategorySelectionServiceImpl implements MemberSubCategorySelectionService {
  private final MemberSubCategorySelectionRepository memberSubCategorySelectionRepository;
  private final SubCategoryRepository subCategoryRepository;

  @Override
  @Transactional
  public void selectSubCategories(Member member, List<Long> categoryIds) {

    memberSubCategorySelectionRepository.deleteAllByMember(member);

    List<SubCategory> categories = subCategoryRepository.findAllInIds(categoryIds);

    if (categories.size() != categoryIds.size()) {
      throw new CategoryException(INVALID_CATEGORY_ID);
    }

    List<MemberSubCategorySelection> memberSubCategorySelections =
        categories.stream()
            .map(
                category ->
                    MemberSubCategorySelection.builder()
                        .member(member)
                        .subCategory(category)
                        .build())
            .collect(Collectors.toList());

    memberSubCategorySelectionRepository.saveAll(memberSubCategorySelections);
  }
}
