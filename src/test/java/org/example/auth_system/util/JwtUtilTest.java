package org.example.auth_system.util;

import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.role.entity.EmployeeRole;
import org.example.auth_system.global.util.JwtUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    @Test
    public void test_nickname(){
        String testNick = "john doe";
        Employee employee = Employee.builder()
                .kakaoNickName(testNick)
                .build();

        String token = JwtUtil.createToken(employee);

        assertEquals(testNick, JwtUtil.parseToken(token).get("nickname"));
    }

    @Test
    public void test_role(){
        EmployeeRole employeeRole1 = EmployeeRole.builder()
                .id(1L)
                .name("role1")
                .build();

        EmployeeRole employeeRole2 = EmployeeRole.builder()
                .id(2L)
                .name("role2")
                .build();

        Employee employee = Employee.builder().build();
        employee.addRole(employeeRole1);
        employee.addRole(employeeRole2);

        String token = JwtUtil.createToken(employee);

        @SuppressWarnings("unchecked")
        List<String> res = (List<String>) JwtUtil.parseToken(token).get("roles");

        // 5) 검증
        assertEquals(2, res.size(), "토큰에 담긴 role 개수가 올바른지");
        assertTrue(res.contains("role1"), "role1이 포함되어야 함");
        assertTrue(res.contains("role2"), "role2이 포함되어야 함");

    }
}
