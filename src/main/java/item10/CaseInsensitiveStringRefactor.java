package item10;

import java.util.Objects;

public final class CaseInsensitiveStringRefactor {
    public final String s;

    public CaseInsensitiveStringRefactor(String s) {
        this.s = Objects.requireNonNull(s);
    }

    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o) {
        return o instanceof CaseInsensitiveStringRefactor && ((CaseInsensitiveStringRefactor) o).s.equalsIgnoreCase(s);
    }
}


