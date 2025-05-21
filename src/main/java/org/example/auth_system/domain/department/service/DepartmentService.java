package org.example.auth_system.domain.department.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.department.dto.response.DepartmentResponse;
import org.example.auth_system.domain.department.entity.Department;
import org.example.auth_system.domain.department.repository.DepartmentRepository;
import org.example.auth_system.domain.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> listDepartments(){
        return departmentRepository.findAll().stream()
                .map(DepartmentResponse::from)
                .collect(Collectors.toList());
    }


}
