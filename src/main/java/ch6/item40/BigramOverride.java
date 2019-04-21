package ch6.item40;

import java.util.HashSet;
import java.util.Set;

// Can you spot the bug?
public class BigramOverride {
    private final char first;
    private final char second;

    public BigramOverride(char first, char second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BigramOverride)) return false;
        BigramOverride b = (BigramOverride) o;
        return b.first == first && b.second == second;
    }
    public int hashCode() {
        return 31 * first + second;
    }

    public static void main(String[] args) {
        Set<BigramOverride> s = new HashSet<>();
        for (int i = 0; i < 10; i++)
            for (char ch = 'a'; ch <= 'z'; ch++)
                s.add(new BigramOverride(ch, ch));
        System.out.println(s.size());
    }
}
