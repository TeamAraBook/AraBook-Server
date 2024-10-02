package com.arabook.arabook.storage.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.arabook.arabook.storage.domain.category.entity.Category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member_category_selections")
public class MemberCategorySelection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberCategorySelectionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private Category category;

  @Builder
  private MemberCategorySelection(Member member, Category category) {
    this.member = member;
    this.category = category;
  }
}
