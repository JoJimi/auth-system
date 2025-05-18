package org.example.auth_system.domain.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.auth_system.domain.employee.mapping.EmployeeRoleMapping;
import org.example.auth_system.domain.role.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Long departmentId;

    private String kakaoNickName;

    @Builder.Default
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeRoleMapping> employeeRoles = new HashSet<>();

    // 편의 메서드: Role 추가
    public void addRole(Role role) {
        EmployeeRoleMapping mapping = EmployeeRoleMapping.builder()
                .employee(this)
                .role(role)
                .build();
        employeeRoles.add(mapping);
        role.getEmployeeRoles().add(mapping);
    }

    // 편의 메서드: Role 제거
    public void removeRole(Role role) {
        employeeRoles.removeIf(mapping ->
                mapping.getEmployee().equals(this) &&
                        mapping.getRole().equals(role)
        );
        role.getEmployeeRoles().removeIf(mapping ->
                mapping.getEmployee().equals(this) &&
                        mapping.getRole().equals(role)
        );
    }
}

