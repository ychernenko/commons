package ychernenko.commons.batch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class BatchIteratorTest {

    @Test
    void test() {
        var lists = List.of(
            List.of(1, 2),
            List.of(3, 4, 5)
        );
        var expected = lists
            .stream()
            .flatMap(Collection::stream)
            .collect(toList());

        var actual = new ArrayList<Integer>();
        new BatchIterator<>(lists.iterator())
            .forEachRemaining(actual::add);

        assertThat(actual).isEqualTo(expected);
    }

}