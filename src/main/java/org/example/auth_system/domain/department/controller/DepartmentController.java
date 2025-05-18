package org.example.auth_system.domain.department.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.department.entity.Department;
import org.example.auth_system.domain.department.service.DepartmentService;
import org.example.auth_system.domain.employee.entity.Employee;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = "/departments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> findAllEmployee(){
        return ResponseEntity.ok(departmentService.listDepartments());
    }

}
