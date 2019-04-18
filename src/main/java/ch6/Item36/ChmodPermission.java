package ch6.Item36;

// Bit field enumeration constants - OBSOLETE!
public class ChmodPermission {
    public static final int READ = 1 << 2;  // 4
    public static final int WRITE = 1 << 1;  // 2
    public static final int EXECUTE = 1 << 0;  // 1

    // Parameter is bitwise OR of zero or more constants
    public static String getPermission(int permissions) {
        if (permissions == 0)
            return "";
        if (permissions == 1)
            return "X";
        if (permissions == 2)
            return "W";
        if (permissions == 3)
            return "RX";
        if (permissions == 4)
            return "R";
        if (permissions == 5)
            return "RX";
        if (permissions == 6)
            return "RW";
        if (permissions == 7)
            return "RWX";
        throw new AssertionError("Unknown op");
    }

    public static void main(String[] args) {
        System.out.println(getPermission(READ | WRITE | EXECUTE));
    }
}
