package com.fedex.failovercontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class InfoDetailsContributor implements InfoContributor {

    private final HealthStatusRepository healthStatusRepository;

    @Override
    public void contribute(Info.Builder builder) {
        List<HealthStatus> statuses = healthStatusRepository.findAll();
        Map<String, Object> userDetails = new HashMap<>();
        if (!statuses.isEmpty()) {
            userDetails.put("lastUpTime", statuses.get(0).getLastHealthyTime());
        }
        builder.withDetail("health", userDetails);
    }
}
