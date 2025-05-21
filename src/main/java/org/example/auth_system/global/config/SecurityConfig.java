package org.example.auth_system.global.config;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.repository.EmployeeRepository;
import org.example.auth_system.global.security.custom.CustomAuthenticationEntryPoint;
import org.example.auth_system.global.security.filter.JwtAuthFilter;
import org.example.auth_system.global.service.KakaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private static final String[] AUTH_ALLOWLIST = {
            "/swagger-ui/**",
            "/v3/**",
            "/login/**",
            "/images/**",
            "/kakao/**"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable())
                .addFilterBefore(new JwtAuthFilter(kakaoService, employeeRepository), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_ALLOWLIST).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(customAuthenticationEntryPoint))
                .build();

    }

}
