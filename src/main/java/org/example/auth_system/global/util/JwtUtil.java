package org.example.auth_system.global.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.mapping.EmployeeRoleMapping;
import org.example.auth_system.domain.role.entity.EmployeeRole;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long expirationTimeInMillis = 1000 * 60 * 60;

    public static String createToken(Employee employee){

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

}
