package org.example.auth_system.domain.employee.controller;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> findAllEmployee(){
        return ResponseEntity.ok(employeeService.listEmployees());
    }

}
