package org.example.auth_system.domain.employee.repository;

import org.example.auth_system.domain.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByKakaoNickName(String kakaoNickName);

    Employee findByKakaoNickName(String nickName);
}
