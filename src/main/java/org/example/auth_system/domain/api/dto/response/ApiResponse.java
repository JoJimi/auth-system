package org.example.auth_system.domain.api.dto.response;

import lombok.Getter;
import org.example.auth_system.domain.api.entity.Api;

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
