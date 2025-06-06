package org.example.auth_system.global.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.repository.EmployeeRepository;
import org.example.auth_system.global.dto.response.KakaoUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final KakaoService kakaoService;
    private final EmployeeRepository employeeRepository;

    public ResponseEntity login(String code) {
        String token = kakaoService.getAccessTokenFromKakao(code);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    public ResponseEntity getKakaoUser(String token){
        KakaoUserResponse dto = kakaoService.getUserFromKakao(token);
        String nickName = dto.getKakaoAccount().getProfile().getNickname();

        if(employeeRepository.existsByKakaoNickName(nickName)){
            return new ResponseEntity("환영합니다. " + nickName, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("등록된 임직원이 아닙니다.", HttpStatus.FORBIDDEN);
        }
    }
}
