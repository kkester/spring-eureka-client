package com.fedex.failovercontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HealthStatus {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String status;
    private LocalDateTime lastHealthyTime;
}
