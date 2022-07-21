package ychernenko.commons.batch;

public interface Batches {

    static <T> BatchIterator<T> iterator(BatchSource<T> source, int size, int maxBatches) {
        return new BatchIterator<>(new BatchSourceIterator<>(source, size, maxBatches));
    }

    static <T> BatchIterator<T> iterator(BatchSource<T> source, int size, int maxBatches, int startingBatch) {
        return new BatchIterator<>(new BatchSourceIterator<>(source, size, maxBatches, startingBatch));
    }

    static <T> Iterable<T> iterable(BatchSource<T> source, int size, int maxBatches) {
        return new BatchIterable<>(() -> iterator(source, size, maxBatches));
    }

    static <T> Iterable<T> iterable(BatchSource<T> source, int size, int maxBatches, int startingBatch) {
        return new BatchIterable<>(() -> iterator(source, size, maxBatches, startingBatch));
    }
}
