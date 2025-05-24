package org.example.auth_system.global.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.api.entity.Api;
import org.example.auth_system.domain.api.repository.ApiRepository;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.app.repository.AppRepository;
import org.example.auth_system.global.dto.request.ValidateTokenRequest;
import org.example.auth_system.global.dto.response.AppTokenResponse;
import org.example.auth_system.global.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AppRepository appRepository;
    private final ApiRepository apiRepository;
    public AppTokenResponse createAppToken(Long appId){
        App app = appRepository.getById(appId);
        String token = JwtUtil.createAppToken(app);

        return AppTokenResponse.builder()
                .token(token)
                .build();
    }

    public ResponseEntity<String> validateToken(ValidateTokenRequest request) {

        Api api = apiRepository.findByMethodAndPath(request.getMethod(), request.getPath());

        return JwtUtil.validateToken(request, api);
    }
}
