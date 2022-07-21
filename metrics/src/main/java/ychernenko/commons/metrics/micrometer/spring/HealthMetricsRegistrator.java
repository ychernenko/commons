package ychernenko.commons.metrics.micrometer.spring;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.boot.actuate.health.CompositeHealth;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

@Component
public class HealthMetricsRegistrator {

    private final HealthEndpoint healthEndpoint;
    private final MeterRegistry meterRegistry;

    public HealthMetricsRegistrator(HealthEndpoint healthEndpoint, MeterRegistry meterRegistry) {
        this.healthEndpoint = healthEndpoint;
        this.meterRegistry = meterRegistry;
    }

    @EventListener
    public void handle(ApplicationStartedEvent event) {
        registerAllHealthMetrics();
    }

    public void registerAllHealthMetrics() {
        var queue = new ArrayDeque<Map.Entry<List<String>, HealthComponent>>();
        queue.add(Map.entry(emptyList(), healthEndpoint.health()));
        while (!queue.isEmpty()) {
            var entry = queue.poll();
            var path = entry.getKey();
            var health = entry.getValue();
            var name = path.size() > 0 ? String.join(".", path) : "root";
            register(name, path);
            if (health instanceof CompositeHealth) {
                var compositeHealth = (CompositeHealth) health;
                compositeHealth.getComponents().forEach((childName, childHealth) -> {
                    var childPath = new ArrayList<>(path);
                    childPath.add(childName);
                    queue.add(Map.entry(childPath, childHealth));
                });
            }
        }
    }

    public void register(String name, List<String> path) {
        List.of(
            Status.UNKNOWN,
            Status.UP,
            Status.DOWN,
            Status.OUT_OF_SERVICE
        ).forEach(status ->
            meterRegistry.gauge(
                "health",
                List.of(
                    Tag.of("name", name),
                    Tag.of("status", status.getCode())
                ),
                status,
                expectedStatus -> {
                    var actualStatus = healthEndpoint.healthForPath(path.toArray(new String[]{})).getStatus();
                    return expectedStatus.equals(actualStatus) ? 1 : 0;
                })
        );
    }
}
