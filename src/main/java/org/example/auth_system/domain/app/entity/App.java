package org.example.auth_system.domain.app.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import org.example.auth_system.domain.employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class App {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "123", description = "auto increment pk")
    private Long id;

    @Schema(example = "vacation", description = "시스템 이름")
    private String name;

}
