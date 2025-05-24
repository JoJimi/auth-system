package org.example.auth_system.global.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppTokenResponse {
    private String token;
}
