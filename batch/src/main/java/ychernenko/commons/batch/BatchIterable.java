package ychernenko.commons.batch;

import java.util.Iterator;
import java.util.function.Supplier;

public class BatchIterable<T> implements Iterable<T> {

    private final Supplier<BatchIterator<T>> iteratorSupplier;

    public BatchIterable(Supplier<BatchIterator<T>> iteratorSupplier) {
        this.iteratorSupplier = iteratorSupplier;
    }

    @Override
    public Iterator<T> iterator() {
        return iteratorSupplier.get();
    }
}
