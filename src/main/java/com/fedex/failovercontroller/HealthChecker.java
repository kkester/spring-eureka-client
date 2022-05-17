package com.fedex.failovercontroller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class HealthChecker {

    private final EurekaClient eurekaClient;
    private final HealthStatusRepository healthStatusRepository;

    @Scheduled(fixedDelay = 1000)
    void checkHealth() {
        log.info("Checking health");
        Applications applications = eurekaClient.getApplications();
        healthStatusRepository.save(HealthStatus.builder()
                .status("UP")
                .lastHealthyTime(LocalDateTime.now())
                .build());
    }
}
