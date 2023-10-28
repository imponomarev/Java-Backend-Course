package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Task8 {

    class BackwardIterator<T> implements Iterator<T> {

        private final T[] values;

        private int currInd;

        public BackwardIterator(Collection<T> collection) {
            this.values = (T[]) collection.toArray();
            this.currInd = values.length - 1;
        }

        @Override
        public boolean hasNext() {
            return currInd >= 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return values[currInd--];
        }
    }
}
