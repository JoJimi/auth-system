package org.example.auth_system.domain.app.service;

import lombok.RequiredArgsConstructor;
import org.example.auth_system.domain.app.dto.response.AppResponse;
import org.example.auth_system.domain.app.entity.App;
import org.example.auth_system.domain.app.repository.AppRepository;
import org.example.auth_system.domain.department.dto.response.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<AppResponse> listApps() {
        return appRepository.findAll().stream()
                .map(AppResponse::from)
                .collect(Collectors.toList());
    }
}
