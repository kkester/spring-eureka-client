package com.fedex.failovercontroller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HealthStatusRepository extends JpaRepository<HealthStatus, UUID> {
}
