package org.example.auth_system.domain.app.dto.response;

import org.example.auth_system.domain.app.entity.App;

public record AppResponse(
        Long id,
        String name
) {
    public static AppResponse from(App app) {
        return new AppResponse(
                app.getId(),
                app.getName()
        );
    }
}
