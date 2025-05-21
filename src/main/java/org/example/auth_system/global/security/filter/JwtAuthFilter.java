package org.example.auth_system.global.security.filter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.repository.EmployeeRepository;
import org.example.auth_system.global.dto.response.KakaoUserResponse;
import org.example.auth_system.global.service.KakaoService;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            String accessToken = authorizationHeader.substring(7);
            String nickName = kakaoService.getUserFromKakao(accessToken).getKakaoAccount().getProfile().getNickname();

            if(employeeRepository.existsByKakaoNickName(nickName)){
                Employee employee = employeeRepository.findByKakaoNickName(nickName);
                Authentication authentication = new TestingAuthenticationToken(employee.getFirstName(), "password", "ROLE_TEST");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
