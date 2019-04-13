package ch5.item31;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static ch5.item31.GenericMethodBoundedWildcard.union;

public class TestGeneric {

    @Test
    public void testGenericWildcard(){
        StackGenericWildcard<Number> numberStack = new StackGenericWildcard<>();
        Collection<Integer> integers = new ArrayList<>(List.of(1,2,3,4));
        numberStack.pushAll(integers);

        Collection<Number> objects = new ArrayList<>();
        numberStack.popAll(objects);

        Assert.assertEquals(objects.size(),4);

    }

    @Test
    public void testGenericMethodWildcard() {
        Set<Integer> integers = Set.of(1, 3, 5);
        Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
        Set<Number> numbers = union(integers, doubles);

        // Explicit type parameter - required prior to Java 8
        Set<Number> numbers2 = GenericMethodBoundedWildcard.<Number>union(integers, doubles);
    }

}
