package ychernenko.commons.metrics.micrometer.tags;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

public class TaggedTimer extends TaggedMetric<Timer> {

    public TaggedTimer(String name, MeterRegistry meterRegistry) {
        super(tags -> meterRegistry.timer(name, tags));
    }
}
