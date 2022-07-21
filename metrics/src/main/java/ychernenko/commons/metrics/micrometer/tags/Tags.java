package ychernenko.commons.metrics.micrometer.tags;

import io.micrometer.core.instrument.Tag;

import java.util.HashSet;
import java.util.Set;

public interface Tags {

    static Set<Tag> asTags(String ... tagPairs) {
        if (tagPairs.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        var tags = new HashSet<Tag>();
        for (int i=0; i<tagPairs.length-1; i=i+2) {
            var kay = tagPairs[i];
            var value = String.valueOf(tagPairs[i+1]);
            tags.add(Tag.of(kay, value));
        }
        return tags;
    }
}
