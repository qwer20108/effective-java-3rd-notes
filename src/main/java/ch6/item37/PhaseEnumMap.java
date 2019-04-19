package ch6.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

// Using a nested EnumMap to associate data with enum pairs
public enum PhaseEnumMap {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID), SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);
        private final PhaseEnumMap from;
        private final PhaseEnumMap to;

        Transition(PhaseEnumMap from, PhaseEnumMap to) {
            this.from = from;
            this.to = to;
        }

        // Initialize the phase transition map
        private static final Map<PhaseEnumMap, Map<PhaseEnumMap, Transition>> m =
                Stream.of(values())
                        .collect(
                                groupingBy(t -> t.from,
                                        () -> new EnumMap<>(PhaseEnumMap.class),
                                        toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(PhaseEnumMap.class))
                                ));

        public static Transition from(PhaseEnumMap from, PhaseEnumMap to) {
            return m.get(from).get(to);
        }
    }
}
