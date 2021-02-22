package br.com.zup.proposta.config.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckIndicator implements HealthIndicator {

    @Autowired
    private ApplicationAvailability applicationAvailability;

    @Override
    public Health health() {
        if(check()) {
            return Health.up().build();
        }
        return Health.down().build();

    }

    boolean check() {
        LivenessState livenessState = applicationAvailability.getLivenessState();
        ReadinessState readinessState = applicationAvailability.getReadinessState();
        if(livenessState.equals(LivenessState.BROKEN) || readinessState.equals(ReadinessState.REFUSING_TRAFFIC)) {
            return false;
        } else {
            return true;
        }
    }

}
