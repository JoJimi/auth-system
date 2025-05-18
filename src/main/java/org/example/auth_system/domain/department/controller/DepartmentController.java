package org.example.auth_system.domain.department.controller;

import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name="Basics", description = "기본 관리 API")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(description = "전사 부서 조회")
    @GetMapping(value = "/departments",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> findAllEmployee(){
        return ResponseEntity.ok(departmentService.listDepartments());
    }

}
