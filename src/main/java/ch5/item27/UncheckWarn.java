package ch5.item27;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UncheckWarn {
    private static class User {
        User(String username){
            this.username = username;
        }
        String username;

    }
    public static void main(String[] args) {
        Set<UncheckWarn> exaltation = new HashSet();
        Set<UncheckWarn> exaltation2 = new HashSet<>();

        // create an empty array list with an initial capacity
        ArrayList<Integer> arrlist = new ArrayList<Integer>();

        // use add() method to add values in the list
        arrlist.add(10);
        arrlist.add(12);
        arrlist.add(31);
        arrlist.add(49);

        System.out.println("Printing elements of array1");

        // let us print all the elements available in list
        for (Integer number : arrlist) {
            System.out.println("Number = " + number);
        }

        // toArray copies content into other array
        Integer list2[] = new Integer[3];
        list2 = arrlist.toArray(list2);

        System.out.println("Printing elements of array2");

        // let us print all the elements available in list
        for (Integer number : list2) {
            System.out.println("Number = " + number);
        }
    }


}
