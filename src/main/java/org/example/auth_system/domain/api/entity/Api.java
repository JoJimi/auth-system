package org.example.auth_system.domain.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.department.entity.Department;

import java.util.List;

@Entity
@Getter
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "123", description = "auto increment pk")
    private Long id;

    @Schema(example = "GET", description = "http_method")
    private String method;

    @Schema(example = "/vacation", description = "application api path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "app_id", nullable = false)
    private App app;
}
