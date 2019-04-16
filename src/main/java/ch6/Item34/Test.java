package ch6.Item34;

public class Test {
    // The int enum pattern - severely deficient!
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;
    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    public static void main(String[] args) {
        // Tasty citrus flavored applesauce!
        int i = (APPLE_FUJI - ORANGE_TEMPLE) / APPLE_PIPPIN;

        double x = Double.parseDouble("2");
        double y = Double.parseDouble("4");
        for (OperationConstantSpecific2 op : OperationConstantSpecific2.values()) System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
}
