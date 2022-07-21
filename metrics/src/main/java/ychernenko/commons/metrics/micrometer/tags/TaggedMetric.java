package ychernenko.commons.metrics.micrometer.tags;

import io.micrometer.core.instrument.Tag;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class TaggedMetric<T> {

    private final Map<Set<Tag>, T> metrics = new ConcurrentHashMap<>();
    private final Function<Iterable<Tag>, T> metricBuilder;

    public TaggedMetric(Function<Iterable<Tag>, T> metricBuilder) {
        this.metricBuilder = metricBuilder;
    }

    public T withTags(String tagKey, String tagValue) {
        return withTags(Set.of(Tag.of(tagKey, tagValue)));
    }

    public T withTags(String ... tagPairs) {
        return withTags(Tags.asTags(tagPairs));
    }

    public T withTags(Set<Tag> tags) {
        return metrics.computeIfAbsent(tags, metricBuilder);
    }
}
