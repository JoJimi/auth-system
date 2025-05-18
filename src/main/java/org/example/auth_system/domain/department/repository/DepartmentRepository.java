package org.example.auth_system.domain.department.repository;

import org.example.auth_system.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
