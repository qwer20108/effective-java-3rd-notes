package ch5.item28;

import java.util.ArrayList;
import java.util.List;

public class ArrayReifiedTest {
    public static void main(String[] args) {
//        Object[] objectArray = new Long[1];
//        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

//        List<Object> ol = new ArrayList<Long>(); // Incompatible types
//        ol.add("I don't fit in");

//        List<String>[] stringLists = new List<String>[1];  // (1)
        List<String>[] stringLists = (List<String>[]) new List<?>[1]; //test
        List<Integer> intList = List.of(42);// (2)
        Object[] objects = stringLists;// (3)
        objects[0] = intList;// (4)
        String s = stringLists[0].get(0);// (5)
    }
}
