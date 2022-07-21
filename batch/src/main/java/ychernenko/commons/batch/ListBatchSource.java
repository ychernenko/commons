package ychernenko.commons.batch;

import java.util.List;

import static java.lang.Math.min;

public class ListBatchSource<T> implements BatchSource<T> {

    private final List<T> list;

    public ListBatchSource(List<T> list) {
        this.list = list;
    }

    @Override
    public List<T> getBatch(int size, int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number: " + number);
        }
        if (size < 0) {
            throw new IllegalArgumentException("size: " + number);
        }
        return list.subList(
            min(list.size(), size * number),
            min(list.size(), size * (number + 1))
        );
    }
}
