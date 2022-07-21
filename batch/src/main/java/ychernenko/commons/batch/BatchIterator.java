package ychernenko.commons.batch;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BatchIterator<T> implements Iterator<T> {

    private final Iterator<List<T>> allBatches;
    private Iterator<T> currentBatch = Collections.emptyIterator();

    public BatchIterator(Iterator<List<T>> allBatches) {
        this.allBatches = allBatches;
    }

    @Override
    public boolean hasNext() {
        if (!currentBatch.hasNext() && allBatches.hasNext()) {
            currentBatch = allBatches.next().iterator();
        }
        return currentBatch.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentBatch.next();
    }
}
