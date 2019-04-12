package ch5.item31;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

// Initial attempt to generify Stack - won't compile!
public class StackGeneric<E> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackGeneric() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    // Appropriate suppression of unchecked warning
    public E pop() {
        if (size == 0) throw new EmptyStackException();
        // push requires elements to be of type E, so cast is correct
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

//    // pushAll method without wildcard type - deficient!
//    public void pushAll(Iterable<E> src) {
//        for (E e : src) push(e);
//    }

    // Wildcard type for a parameter that serves as an E producer
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) push(e);
    }

    // popAll method without wildcard type - deficient!
//    public void popAll(Collection<E> dst) {
//        while (!isEmpty()) dst.add(pop());
//    }

    // Wildcard type for parameter that serves as an E consumer
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) dst.add(pop());
    }

    public boolean isEmpty(){
        return size == 0;
    }
}

