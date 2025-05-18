package org.example.auth_system.domain.employee.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.dto.response.EmployeeResponse;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name="Basics", description = "기본 관리 API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeResponse>> findAllEmployee(){
        return ResponseEntity.ok(employeeService.listEmployees());
    }

    @PostMapping(value = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeResponse> create(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam Long departmentId){
        EmployeeResponse dto = employeeService.createEmployee(firstName, lastName, departmentId);
        return ResponseEntity
                .created(URI.create("/employees/" + dto.id()))
                .body(dto);
    }
}
