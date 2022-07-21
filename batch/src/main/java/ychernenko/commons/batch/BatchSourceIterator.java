package ychernenko.commons.batch;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BatchSourceIterator<T> implements Iterator<List<T>> {

    private final BatchSource<T> source;
    private final int size;
    private final int maxBatches;
    private int number;
    private List<T> batch;
    private boolean lastBatchFull = true;

    public BatchSourceIterator(BatchSource<T> source, int size, int maxBatches, int startingBatchNumber) {
        this(source, size, maxBatches);
        this.number = startingBatchNumber;
    }

    public BatchSourceIterator(BatchSource<T> source, int size, int maxBatches) {
        this.source = source;
        this.size = size;
        this.maxBatches = maxBatches;
    }

    @Override
    public boolean hasNext() {
        if (batch != null && !batch.isEmpty())
            return true;
        if ((number >= maxBatches && maxBatches > 0)
            || size == 0
            || maxBatches == 0
            || !lastBatchFull
        ) {
            return false;
        }
        batch = source.getBatch(size, number++);
        lastBatchFull = batch.size() >= size;
        return !batch.isEmpty();
    }

    @Override
    public List<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        List<T> tmp = batch;
        batch = null;
        return tmp;
    }
}
