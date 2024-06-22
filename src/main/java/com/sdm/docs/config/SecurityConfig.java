package com.sdm.docs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Slf4j
@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .cors(cors -> {
        CorsConfiguration corsConfiguration = corsConfiguration();
        cors.configurationSource(request -> corsConfiguration);
      }) // CORS 설정
      .csrf(AbstractHttpConfigurer::disable) // CSRF 설정
      .formLogin(AbstractHttpConfigurer::disable) // 폼 로그인 설정
      .authorizeHttpRequests(authorizeRequests -> {
        authorizeRequests.anyRequest().permitAll();
      }) // 요청에 대한 권한 설정
//      .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 설정
      .logout(AbstractHttpConfigurer::disable) // 로그아웃 설정
    ;
    return http.build();
  }

  CorsConfiguration corsConfiguration() {
    List<String> allowMethod = List.of("OPTIONS", "GET", "POST", "PUT", "DELETE");
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("http://localhost");
    corsConfiguration.setAllowedMethods(allowMethod);
    corsConfiguration.addAllowedHeader("*");

    return corsConfiguration;
  }
}
