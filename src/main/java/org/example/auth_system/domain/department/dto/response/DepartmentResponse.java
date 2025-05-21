package org.example.auth_system.domain.department.dto.response;


import org.example.auth_system.domain.department.entity.Department;
import org.example.auth_system.domain.employee.dto.response.EmployeeResponse;

import java.util.List;
import java.util.stream.Collectors;

public record DepartmentResponse(
        Long id,
        String deptName,
        List<EmployeeResponse> employees
) {
    public static DepartmentResponse from(Department dept) {
        List<EmployeeResponse> employees = dept.getEmployees().stream()
                .map(EmployeeResponse::from)
                .collect(Collectors.toList());

        return new DepartmentResponse(
                dept.getId(),
                dept.getDeptName(),
                employees
        );
    }
}
