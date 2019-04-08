package ch5.item26;

import java.util.*;

public class RawType {
    // Raw collection type -don't do this!
    // My stamp collection. Contains only Stamp instances.
    private final Collection stamps;

    public RawType() {
        this.stamps = new ArrayList<>();
    }

    private static class Stamp {
        public void cancel(){};
    }

    private static class Coin {

    }
    public void test(){
        stamps.add(new Coin());
        for (Iterator i = stamps.iterator(); i.hasNext(); ){
            Stamp stamp = (Stamp) i.next(); // Throws ClassCastException
            stamp.cancel();
        }



    }

    // Fails at runtime - unsafeAdd method uses a raw type (List)!
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAddRaw(strings, Integer.valueOf(42));
        String s = strings.get(0); // Has compiler-generated cast
    }

    private static void unsafeAdd(List<Object> list, Object o) {
        list.add(o);
    }

    private static void unsafeAddRaw(List list, Object o) {
        list.add(o);
    }

    // Use of raw type for unknown element type - don't do this!
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1) if (s2.contains(o1)) result++;
        return result;
    }
    // Uses unbounded wildcard type - typesafe and flexible
    static int numElementsInCommonUnboundedWildcardType(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) if (s2.contains(o1)) result++;
        return result;
    }


}
