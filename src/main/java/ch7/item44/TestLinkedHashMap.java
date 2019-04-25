package ch7.item44;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {
    private static final int MAX_ENTRIES = 5;


    public static void main(String[] args) {
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(MAX_ENTRIES + 1, .75F, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_ENTRIES;
            }
        };

        linkedHashMap.put(0, "H");
        linkedHashMap.put(1, "E");
        linkedHashMap.put(2, "L");
        linkedHashMap.put(3, "L");
        linkedHashMap.put(4, "O");
        linkedHashMap.put(6, "W");
        linkedHashMap.put(7, "O");
        linkedHashMap.put(8, "R");


        System.out.println("" + linkedHashMap);
    }
}
