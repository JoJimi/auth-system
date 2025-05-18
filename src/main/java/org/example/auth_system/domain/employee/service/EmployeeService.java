package org.example.auth_system.domain.employee.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.employee.dto.response.EmployeeResponse;
import org.example.auth_system.domain.employee.entity.Employee;
import org.example.auth_system.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> listEmployees(){
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::from)
                .collect(Collectors.toList());
    }

    public EmployeeResponse createEmployee(String firstName, String lastName, Long departmentId, String kakaoNickName) {
        if(employeeRepository.existsByKakaoNickName(kakaoNickName)){
            throw new DuplicateRequestException("같은 카카오 닉네임이 존재합니다.");
        }

        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .departmentId(departmentId)
                .kakaoNickName(kakaoNickName)
                .build();

        employeeRepository.save(employee);
        return EmployeeResponse.from(employee);
    }
}
