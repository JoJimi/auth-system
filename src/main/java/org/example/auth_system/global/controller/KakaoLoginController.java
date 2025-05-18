package org.example.auth_system.global.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth_system.domain.employee.repository.EmployeeRepository;
import org.example.auth_system.global.dto.response.KakaoUserResponse;
import org.example.auth_system.global.service.KakaoService;
import org.example.auth_system.global.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoLoginController {

    private final LoginService loginService;

    @GetMapping("/kakao/callback")
    public ResponseEntity callback(@RequestParam("code")String code){
        return loginService.login(code);
    }
}
