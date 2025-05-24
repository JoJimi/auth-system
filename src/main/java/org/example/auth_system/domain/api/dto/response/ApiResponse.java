package org.example.auth_system.domain.api.dto.response;

import org.example.auth_system.domain.api.entity.Api;
import org.example.auth_system.domain.employee.dto.response.EmployeeResponse;
import org.example.auth_system.domain.employee.entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public record ApiResponse(
        Long id,
        String method,
        String path,
        Long appId
) {
    public static ApiResponse from(Api api) {

        return new ApiResponse(
                api.getId(),
                api.getMethod(),
                api.getPath(),
                api.getApp().getId()
        );
    }
}
