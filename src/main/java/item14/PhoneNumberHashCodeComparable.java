package item14;


import java.util.Comparator;

// Class with a typical equals method
public class PhoneNumberHashCodeComparable implements Comparable<PhoneNumberHashCodeComparable> {
    private final short areaCode, prefix, lineNum;

    public PhoneNumberHashCodeComparable(int areaCode, int prefix, int lineNum) {
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
        if (!(o instanceof PhoneNumberHashCodeComparable)) return false;
        PhoneNumberHashCodeComparable pn = (PhoneNumberHashCodeComparable) o;
        return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
    }

    // Typical hashCode method
    @Override
    public int hashCode() {
        int result = Short.hashCode(areaCode);
        result = 31 * result + Short.hashCode(prefix);
        result = 31 * result + Short.hashCode(lineNum);
        return result;
    }

    // Multiple-field Comparable with primitive fields
//    @Override
//    public int compareTo(PhoneNumberHashCodeComparable phoneNumberHashCodeComparable) {
//        int result = Short.compare(areaCode, phoneNumberHashCodeComparable.areaCode);
//        if (result == 0) {
//            result = Short.compare(prefix, phoneNumberHashCodeComparable.prefix);
//            if (result == 0)
//                result = Short.compare(lineNum, phoneNumberHashCodeComparable.lineNum);
//        }
//        return result;
//    }

    // Comparable with comparator construction methods
    private static final Comparator<PhoneNumberHashCodeComparable> COMPARATOR =
            Comparator.comparingInt((PhoneNumberHashCodeComparable pn) -> pn.areaCode)
                    .thenComparingInt(pn -> pn.prefix)
                    .thenComparingInt(pn -> pn.lineNum);

    public int compareTo(PhoneNumberHashCodeComparable pn) {
        return COMPARATOR.compare(this, pn);
    }


}
