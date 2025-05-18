package org.example.auth_system.domain.department.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.department.entity.Department;
import org.example.auth_system.domain.department.repository.DepartmentRepository;
import org.example.auth_system.domain.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> listDepartments(){
        return departmentRepository.findAll();
    }


}
