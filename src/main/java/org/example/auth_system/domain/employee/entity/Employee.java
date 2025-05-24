package org.example.auth_system.domain.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.auth_system.domain.department.entity.Department;
import org.example.auth_system.domain.employee.mapping.EmployeeRoleMapping;
import org.example.auth_system.domain.role.entity.EmployeeRole;

import java.util.HashSet;
import java.util.Objects;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private String kakaoNickName;

    @Builder.Default
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmployeeRoleMapping> employeeRoles = new HashSet<>();

    // 편의 메서드: Role 추가
    public void addRole(EmployeeRole employeeRole) {
        EmployeeRoleMapping mapping = EmployeeRoleMapping.builder()
                .employee(this)
                .employeeRole(employeeRole)
                .build();
        employeeRoles.add(mapping);
        employeeRole.getEmployeeRoles().add(mapping);
    }

    // 편의 메서드: Role 제거
    public void removeRole(EmployeeRole employeeRole) {
        employeeRoles.removeIf(mapping ->
                mapping.getEmployee().equals(this) &&
                        mapping.getEmployeeRole().equals(employeeRole)
        );
        employeeRole.getEmployeeRoles().removeIf(mapping ->
                mapping.getEmployee().equals(this) &&
                        mapping.getEmployeeRole().equals(employeeRole)
        );
    }

    public static boolean isHR(Employee employee) {
        if (employee == null || employee.getEmployeeRoles() == null) {
            return false;
        }
        return employee.getEmployeeRoles().stream()
                .map(EmployeeRoleMapping::getEmployeeRole)
                .filter(Objects::nonNull)
                .map(EmployeeRole::getName)
                .anyMatch("인사팀"::equals);
    }
}

