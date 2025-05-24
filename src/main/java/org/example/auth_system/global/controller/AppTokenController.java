package org.example.auth_system.global.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.global.dto.response.AppTokenResponse;
import org.example.auth_system.global.service.TokenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
