package ch2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MemoryLeakStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public MemoryLeakStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }
    // memory leak never be dereferenced
    public Object pop() {
        if (size == 0) throw new EmptyStackException();
        return elements[--size];
    }

    /***
         *   Ensure space for at least one more element, roughly
         *   doubling the capacity each time the array needs to grow.
        * */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }


}
