package com.arabook.arabook.api.member.service;

import static com.arabook.arabook.common.exception.category.CategoryExceptionType.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arabook.arabook.common.exception.category.CategoryException;
import com.arabook.arabook.storage.domain.category.entity.SubCategory;
import com.arabook.arabook.storage.domain.category.repository.CategoryRepository;
import com.arabook.arabook.storage.domain.member.entity.Member;
import com.arabook.arabook.storage.domain.member.entity.MemberCategorySelection;
import com.arabook.arabook.storage.domain.member.repository.MemberCategorySelectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCategorySelectionServiceImpl implements MemberCategorySelectionService {
  private final MemberCategorySelectionRepository memberCategorySelectionRepository;
  private final CategoryRepository categoryRepository;

  @Override
  @Transactional
  public void selectCategories(Member member, List<Long> categoryIds) {

    memberCategorySelectionRepository.deleteAllByMember(member);

    List<SubCategory> categories = categoryRepository.findAllById(categoryIds);

    if (categories.size() != categoryIds.size()) {
      throw new CategoryException(INVALID_CATEGORY_ID);
    }

    List<MemberCategorySelection> memberCategorySelections =
        categories.stream()
            .map(
                category -> {
                  return MemberCategorySelection.builder()
                      .member(member)
                      .category(category)
                      .build();
                })
            .collect(Collectors.toList());

    memberCategorySelectionRepository.saveAll(memberCategorySelections);
  }
}
