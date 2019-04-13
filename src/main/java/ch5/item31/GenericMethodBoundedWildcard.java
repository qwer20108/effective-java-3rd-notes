package ch5.item31;

import java.util.*;
import java.util.function.UnaryOperator;

public class GenericMethodBoundedWildcard {

    // Generic method
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

    public static <E extends Comparable<? super E>> E max(Collection<? extends E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("Empty collection");

        E result = null;
        for (E e : c)
            if (result == null || e.compareTo(result) > 0)
                result = Objects.requireNonNull(e);

        return result;
        // IllegalArgumentException if the list is empty. A better alternative would be to return an Optional<E> (Item 55).
    }


//    public static void swap(List<?> list, int i, int j) {
//        list.set(i, list.set(j, list.get(i)));  //can not compile put value to wildcard
//    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }
    // Private helper method for wildcard capture
    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

}
