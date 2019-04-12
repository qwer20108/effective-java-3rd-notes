package ch5.item31;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class TestGeneric {

    @Test
    public void testGeneric(){
        StackGeneric<Number> numberStack = new StackGeneric<>();
        Collection<Integer> integers = new ArrayList<>(List.of(1,2,3,4));
        numberStack.pushAll(integers);

        Collection<Number> objects = new ArrayList<>();
        numberStack.popAll(objects);

        Assert.assertEquals(objects.size(),4);

    }

}
