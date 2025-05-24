package org.example.auth_system.domain.app.dto.response;

import org.example.auth_system.domain.api.dto.response.ApiResponse;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.employee.dto.response.EmployeeResponse;
import org.example.auth_system.domain.role.dto.response.AppRoleResponse;

import java.util.List;
import java.util.stream.Collectors;

public record AppResponse(
        Long id,
        String name,
        List<ApiResponse> apiResponses,
        List<AppRoleResponse> appRoleResponses
) {
    public static AppResponse from(App app) {
        List<ApiResponse> apis = app.getApis().stream()
                .map(ApiResponse::from)
                .collect(Collectors.toList());

        List<AppRoleResponse> appRoleResponses = app.getAppRoles().stream()
                .map(AppRoleResponse::from)
                .collect(Collectors.toList());

        return new AppResponse(
                app.getId(),
                app.getName(),
                apis,
                appRoleResponses
        );
    }
}
