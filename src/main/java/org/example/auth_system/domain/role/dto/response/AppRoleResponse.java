package org.example.auth_system.domain.role.dto.response;

import lombok.Getter;
import org.example.auth_system.domain.api.dto.response.ApiResponse;
import org.example.auth_system.domain.role.entity.AppRole;

public record AppRoleResponse(
        Long id,
        ApiResponse apiResponse,
        Long appId
) {
    public static AppRoleResponse from(AppRole appRole) {

        ApiResponse api = ApiResponse.from(appRole.getApi());

        return new AppRoleResponse(
                appRole.getId(),
                api,
                appRole.getApp().getId()
        );
    }
}
