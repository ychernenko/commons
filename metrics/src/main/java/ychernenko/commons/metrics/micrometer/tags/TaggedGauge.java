package ychernenko.commons.metrics.micrometer.tags;

import io.micrometer.core.instrument.MeterRegistry;
import ychernenko.commons.metrics.micrometer.tags.TaggedGauge.GaugeValue;

import java.util.concurrent.atomic.AtomicReference;

import static java.util.Optional.ofNullable;

public class TaggedGauge<T extends Number> extends TaggedMetric<GaugeValue<T>> {

    public TaggedGauge(String name, MeterRegistry meterRegistry) {
        super(tags -> meterRegistry.gauge(
            name,
            tags,
            new GaugeValue<>(),
            r -> ofNullable(r)
                .map(GaugeValue::get)
                .map(Number::doubleValue)
                .orElse(0d)));
    }

    public static class GaugeValue<T extends Number> {

        private final AtomicReference<T> reference = new AtomicReference<>();

        public void set(T value) {
            reference.set(value);
        }

        public T get() {
            return reference.get();
        }
    }
}
