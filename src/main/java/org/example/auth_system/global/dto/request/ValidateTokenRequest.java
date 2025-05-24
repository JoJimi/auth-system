package org.example.auth_system.global.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidateTokenRequest {
    private String token;

    private Long app;

    private String method;

    private String path;


}
