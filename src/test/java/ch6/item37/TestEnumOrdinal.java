package ch6.item37;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class TestEnumOrdinal {

    private Plant[] garden;
    @Before
    public void init(){
        garden = new Plant[10];
        int index = 0;
        for (Plant plant : garden) {
            if (index % 3 == 0)
                garden[index++] = new Plant("plantA", Plant.LifeCycle.ANNUAL);
            else if (index % 3 == 1)
                garden[index++] = new Plant("plantB", Plant.LifeCycle.BIENNIAL);
            else if (index % 3 == 2)
                garden[index++] = new Plant("plantC", Plant.LifeCycle.PERENNIAL);
        }
    }

    @Test
    public void testOrdinal(){
        // Using ordinal() to index into an array - DON'T DO THIS!
        @SuppressWarnings("unchecked")
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++)
            plantsByLifeCycle[i] = new HashSet<>();
        for (Plant p : garden)
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
        // Print the results
        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
    }

    @Test
    public void testEnumMap() {
        // Using an EnumMap to associate data with an enum
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) plantsByLifeCycle.put(lc, new HashSet<>());
        for (Plant p : garden) plantsByLifeCycle.get(p.lifeCycle).add(p);
        System.out.println(plantsByLifeCycle);
    }

    @Test
    public void testStream() {
        //Naive stream -based approach - unlikely to produce an EnumMap !
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle)));
    }

    @Test
    public void testStreamEnumMap() {
        // Using a stream and an EnumMap to associate data with an enum
        System.out.println(Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(Plant.LifeCycle.class), toSet())));
    }


}
