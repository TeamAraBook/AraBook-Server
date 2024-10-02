package com.arabook.arabook.storage.domain.review.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewTag {
  DISAPPOINTED(
      "https://github.com/TeamAraBook/AraBook-Server/blob/dev/images/card/DISAPPOINTED.png?raw=true",
      "별로였어...",
      "#FFD600"),
  SLIGHTLY_DISAPPOINTED(
      "https://github.com/TeamAraBook/AraBook-Server/blob/dev/images/card/SLIGHTLY_DISAPPOINTED.png?raw=true",
      "조금 아쉬웠어..",
      "#FF8339"),
  AVERAGE(
      "https://github.com/TeamAraBook/AraBook-Server/blob/dev/images/card/AVERAGE.png?raw=true",
      "평범했어.",
      "#7187FF"),
  ENJOYABLE(
      "https://github.com/TeamAraBook/AraBook-Server/blob/dev/images/card/ENJOYABLE.png?raw=true",
      "재밌었어!",
      "#1FD068"),
  LIFE_CHANGING(
      "https://github.com/TeamAraBook/AraBook-Server/blob/dev/images/card/LIFE_CHANGING.png?raw=true",
      "내 인생책이야 !!",
      "#FE5D5C");

  private final String reviewTagIcon;
  private final String reviewTagComment;
  private final String reviewTagColor;
}
