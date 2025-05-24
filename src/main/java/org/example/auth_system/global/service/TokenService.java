package org.example.auth_system.global.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.app.repository.AppRepository;
import org.example.auth_system.global.dto.response.AppTokenResponse;
import org.example.auth_system.global.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AppRepository appRepository;
    public AppTokenResponse createAppToken(Long appId){
        App app = appRepository.getById(appId);
        String token = JwtUtil.createAppToken(app);

        return AppTokenResponse.builder()
                .token(token)
                .build();
    }
}
