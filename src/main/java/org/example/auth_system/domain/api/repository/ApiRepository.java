package org.example.auth_system.domain.api.repository;

import org.example.auth_system.domain.api.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
    Api findByMethodAndPath(String method, String path);
}
