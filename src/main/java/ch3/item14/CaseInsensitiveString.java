package ch3.item14;

import java.util.Objects;

// Single-field Comparable with object reference field
public class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public int compareTo(CaseInsensitiveString caseInsensitiveString) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, caseInsensitiveString.s);
    }
}
