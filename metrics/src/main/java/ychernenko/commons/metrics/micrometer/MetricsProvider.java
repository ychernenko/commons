package ychernenko.commons.metrics.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import ychernenko.commons.metrics.micrometer.tags.TaggedCounter;
import ychernenko.commons.metrics.micrometer.tags.TaggedTimer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class MetricsProvider {

    private final MeterRegistry meterRegistry;
    private final Map<String, Object> metrics = new ConcurrentHashMap<>();

    public MetricsProvider(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public TaggedCounter getCounter(String name) {
        return get(name, n -> new TaggedCounter(n, meterRegistry));
    }

    public TaggedTimer getTimer(String name) {
        return get(name, n -> new TaggedTimer(n, meterRegistry));
    }

    @SuppressWarnings("unchecked")
    private <T> T get(String name, Function<String, T> builder) {
        return (T) metrics.computeIfAbsent(name, builder);
    }
}
