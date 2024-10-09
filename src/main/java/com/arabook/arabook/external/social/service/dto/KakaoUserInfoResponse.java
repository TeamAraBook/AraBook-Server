package com.arabook.arabook.external.social.service.dto;

import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KakaoUserInfoResponse(@NotNull Long id) {}
