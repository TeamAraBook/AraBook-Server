package com.arabook.arabook.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.arabook.arabook.api.global.filter.JwtAuthenticationFilter;
import com.arabook.arabook.api.global.filter.JwtExceptionFilter;

import io.swagger.v3.oas.models.PathItem;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  public static final String[] AUTH_WHITELIST = {
    "/", "/error", "/favicon.ico", "/actuator/health", "/recommend/best-seller"
  };

  public static final String[] AUTH_WHITELIST_WILDCARD = {
    "/webjars/**",
    "/swagger-resources/**",
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "/webjars/**",
    "/auth/**",
    "/css/**",
    "/images/**",
    "/js/**",
    "/h2-console/**",
    "/test/**",
    "/books/**"
  };

  @Value("${spring.web.origin.server}")
  private String serverOrigin;

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(serverOrigin)
            .allowedOriginPatterns(serverOrigin)
            .allowedHeaders("*")
            .allowedMethods(
                PathItem.HttpMethod.GET.name(),
                PathItem.HttpMethod.POST.name(),
                PathItem.HttpMethod.PUT.name(),
                PathItem.HttpMethod.PATCH.name(),
                PathItem.HttpMethod.DELETE.name());
      }
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .headers(
            headerConfig ->
                headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

    http.authorizeHttpRequests(
            auth -> {
              auth.requestMatchers(AUTH_WHITELIST).permitAll();
              auth.requestMatchers(AUTH_WHITELIST_WILDCARD).permitAll();
              auth.anyRequest().authenticated();
            })
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
