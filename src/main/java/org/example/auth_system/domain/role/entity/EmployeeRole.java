package org.example.auth_system.domain.role.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.example.auth_system.domain.employee.mapping.EmployeeRoleMapping;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(example = "인사팀", description = "권한 이름")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "employeeRole", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeRoleMapping> employeeRoles = new HashSet<>();
}
