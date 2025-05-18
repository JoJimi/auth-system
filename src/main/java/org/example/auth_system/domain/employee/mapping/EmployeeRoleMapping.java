package org.example.auth_system.domain.employee.mapping;

import jakarta.persistence.*;
import lombok.*;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.role.entity.Role;

@Entity
@Table(name = "employee_role_mapping")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class EmployeeRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
