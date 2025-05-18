package org.example.auth_system.domain.employee.dto.response;

import org.example.auth_system.domain.employee.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        Long departmentId,
        String kakaoNickName,
        List<Long> roleIds) {
    public static EmployeeResponse from(Employee employee) {
        List<Long> roleIds = employee.getEmployeeRoles().stream()
                .map(mapping -> mapping.getRole().getId())
                .collect(Collectors.toList());

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getKakaoNickName(),
                roleIds
        );
    }

}
