package org.example.auth_system.global.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.global.dto.request.ValidateTokenRequest;
import org.example.auth_system.global.dto.response.AppTokenResponse;
import org.example.auth_system.global.service.TokenService;
import org.example.auth_system.global.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/token")
@Tag(name="App2App Token", description = "app2app token API")
public class AppTokenController {

    private final TokenService tokenService;

    @Operation(description = "토큰 발급")
    @PostMapping(value = "/new/{appId}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppTokenResponse> createNewAppToken(@PathVariable Long appId){
        AppTokenResponse response = tokenService.createAppToken(appId);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "토큰 validation")
    @PostMapping(value = "/validate",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateAppToken(ValidateTokenRequest request){
        return tokenService.validateToken(request);
    }


}
