package ch3.item13;


// Class with a typical equals method
public class PhoneNumberCloneable implements Cloneable {
    private final short areaCode, prefix, lineNum;

    public PhoneNumberCloneable(int areaCode, int prefix, int lineNum) {
        this.areaCode = rangeCheck(areaCode, 999, "area code");
        this.prefix = rangeCheck(prefix, 999, "prefix");
        this.lineNum = rangeCheck(lineNum, 9999, "line num");
    }

    private static short rangeCheck(int val, int max, String arg) {
        if (val < 0 || val > max) throw new IllegalArgumentException(arg + ": " + val);
        return (short) val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof PhoneNumberCloneable)) return false;
        PhoneNumberCloneable pn = (PhoneNumberCloneable) o;
        return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
    }

    // Clone method for class with no references to mutable state
    @Override
    public PhoneNumberCloneable clone() {
        try {
            return (PhoneNumberCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();  // Can't happen}
        }
    }
}
