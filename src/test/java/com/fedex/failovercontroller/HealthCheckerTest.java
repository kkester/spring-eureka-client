package com.fedex.failovercontroller;

import com.netflix.discovery.EurekaClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HealthCheckerTest {

    @InjectMocks
    HealthChecker healthChecker;

    @Mock
    EurekaClient eurekaClient;

    @Mock
    HealthStatusRepository healthStatusRepository;

    @Captor
    ArgumentCaptor<HealthStatus> healthStatusCaptor;

    @Test
    void checkHealth() {

        healthChecker.checkHealth();

        verify(eurekaClient).getApplications();
        verify(healthStatusRepository).save(healthStatusCaptor.capture());

        HealthStatus healthStatus = healthStatusCaptor.getValue();
        assertThat(healthStatus.getStatus()).isEqualTo("UP");
        assertThat(healthStatus.getLastHealthyTime()).isBetween(LocalDateTime.now().minusSeconds(1), LocalDateTime.now());
    }

}