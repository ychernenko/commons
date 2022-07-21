package ychernenko.commons.batch;

import java.util.List;

public interface BatchSource<T> {

    List<T> getBatch(int size, int number);
}
