package ychernenko.commons.batch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ListBatchSourceTest {

    private static Stream<Arguments> test() {
        return Stream.of(
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 0,
                List.of(1, 2)
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 1,
                List.of(3, 4)
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 2,
                List.of(5)
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                2, 3,
                List.of()
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                6, 0,
                List.of(1, 2, 3, 4, 5)
            ),
            Arguments.of(
                List.of(1, 2, 3, 4, 5),
                0, 1,
                List.of()
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void test(List<Integer> list, int size, int number, List<Integer> expected) {
        var actual = new ListBatchSource<>(list).getBatch(size, number);
        assertThat(actual).isEqualTo(expected);
    }

}