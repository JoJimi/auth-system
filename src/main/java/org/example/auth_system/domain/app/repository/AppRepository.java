package org.example.auth_system.domain.app.repository;

import org.example.auth_system.domain.app.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, Long> {
}
