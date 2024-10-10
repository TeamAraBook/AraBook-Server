package com.arabook.arabook.storage.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.arabook.arabook.storage.domain.category.entity.SubCategory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member_sub_category_selections")
public class MemberSubCategorySelection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberSubCategorySelectionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private SubCategory subCategory;

  @Builder
  private MemberSubCategorySelection(Member member, SubCategory subCategory) {
    this.member = member;
    this.subCategory = subCategory;
  }
}
