package ychernenko.commons.metrics.tags;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;
import ychernenko.commons.metrics.micrometer.tags.TaggedCounter;

import static org.assertj.core.api.Assertions.assertThat;

class TaggedCounterTest {

    @Test
    void test() {
        var name = "test";
        var tagKey = "tagKey";
        var tagValue = "tagValue";
        var registry = new SimpleMeterRegistry();
        var counter = new TaggedCounter(name, registry);
        counter.withTags(tagKey, tagValue).increment();
        var registeredCounter = registry.find(name).tag(tagKey, tagValue).counter();
        assertThat(registeredCounter).isNotNull();
        assertThat(registeredCounter.count()).isEqualTo(1);
    }
}