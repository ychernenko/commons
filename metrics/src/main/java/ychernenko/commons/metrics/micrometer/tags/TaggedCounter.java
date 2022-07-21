package ychernenko.commons.metrics.micrometer.tags;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class TaggedCounter extends TaggedMetric<Counter> {

    public TaggedCounter(String name, MeterRegistry meterRegistry) {
        super(tags -> meterRegistry.counter(name, tags));
    }

    public void increment() {
        withTags().increment();
    }
}
