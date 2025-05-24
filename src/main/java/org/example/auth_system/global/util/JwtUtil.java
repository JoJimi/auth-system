package org.example.auth_system.global.util;

import ch.qos.logback.core.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.auth_system.domain.api.entity.Api;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.mapping.EmployeeRoleMapping;
import org.example.auth_system.domain.role.dto.response.AppRoleResponse;
import org.example.auth_system.domain.role.entity.EmployeeRole;
import org.example.auth_system.global.dto.request.ValidateTokenRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long expirationTimeInMillis = 1000 * 60 * 60;

    public static String createAppToken(App app){
        Date now = new Date();
        Date expireAt = new Date(now.getTime() + expirationTimeInMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "app");

        claims.put("roles", app.getAppRoles().stream()
                .map(AppRoleResponse::from)
                .map(ar -> ar.apiResponse().id())
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setSubject(String.valueOf(app.getId()))
                .claims(claims)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(SECRET_KEY)
                .compact();

    }

    public static String createUserToken(Employee employee){

        Date now = new Date();
        Date expireAt = new Date(now.getTime() + expirationTimeInMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put(
                "nickname",
                employee.getKakaoNickName());
        claims.put(
                "roles",
                employee.getEmployeeRoles().stream()
                        .map(EmployeeRoleMapping::getEmployeeRole)
                        .map(EmployeeRole::getName)
                        .collect(Collectors.toSet()));

        return Jwts.builder()
                .setSubject(String.valueOf(employee.getId()))
                .claims(claims)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static ResponseEntity<String> validateToken(ValidateTokenRequest request, Api api) {
        Claims claims;
        try {
            claims = parseToken(request.getToken());
        }catch (Exception e){
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
        }

        Date now = new Date();
        if(claims.getExpiration().after(now)){
            return new ResponseEntity<>("token expired", HttpStatus.UNAUTHORIZED);
        }
        if(!StringUtils.equals("app", claims.get("type").toString())) {
            return new ResponseEntity<>("invalid token type", HttpStatus.UNAUTHORIZED);
        }

        String roles = claims.get("roles").toString();

        if(roles.contains(api.getId().toString())){
            return ResponseEntity.ok("권한이 존재합니다.");
        }else {
            return new ResponseEntity<>("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }
    }
}
