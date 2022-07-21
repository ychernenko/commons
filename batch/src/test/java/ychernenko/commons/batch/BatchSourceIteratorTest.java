package ychernenko.commons.batch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BatchSourceIteratorTest {

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, -1,
                List.of(
                    List.of(1, 2),
                    List.of(3, 4),
                    List.of(5)
                )
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 1,
                List.of(
                    List.of(1, 2)
                )
            ),
            Arguments.of(
                List.of(1),
                2, 1,
                List.of(
                    List.of(1)
                )
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                0, -1,
                List.of()
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 0,
                List.of()
            ),
            Arguments.of(
                List.of(),
                2, 1,
                List.of()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test(List<Integer> list, int batchSize, int maxBatches, List<List<Integer>> expected) {
        var batchSource = new ListBatchSource<>(list);
        var actual = new ArrayList<List<Integer>>();
        new BatchSourceIterator<>(batchSource, batchSize, maxBatches)
            .forEachRemaining(actual::add);

        assertThat(actual).isEqualTo(expected);
    }

}