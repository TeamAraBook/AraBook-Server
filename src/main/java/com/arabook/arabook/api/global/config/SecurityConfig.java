package com.arabook.arabook.api.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.PathItem;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  public static final String[] AUTH_WHITELIST = {
    "/", "/error", "/favicon.ico", "/actuator/health", "/check/profile"
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
            .allowedOrigins(
                serverOrigin, serverTestOrigin, clientOrigin, clientTestOrigin, clientLocalOrigin)
            .allowedOriginPatterns(
                serverOrigin, serverTestOrigin, clientOrigin, clientTestOrigin, clientLocalOrigin)
            .allowedHeaders("*")
            .allowedMethods(
                PathItem.HttpMethod.GET.name(),
                PathItem.HttpMethod.POST.name(),
                PathItem.HttpMethod.PUT.name(),
                PathItem.HttpMethod.PATCH.name());
      }
    };
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> {
              session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
        .headers(
            (headerConfig) ->
                headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

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
