package ch6.item39;

import java.util.ArrayList;
import java.util.List;

// Program containing annotations with a parameter
public class Sample3 {
    @ExceptionTest2(ArithmeticException.class)
    public static void m1() {  // Test should pass
        int i = 0;
        i = i / i;
    }

    @ExceptionTest2(ArithmeticException.class)
    public static void m2() {  // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }

    @ExceptionTest2(ArithmeticException.class)
    public static void m3() { }  // Should fail (no exception)


    // Code containing an annotation with an array parameter
    @ExceptionTest2({IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doublyBad() {
        List<String> list = new ArrayList<>();

        // The spec permits this method to throw either
        // IndexOutOfBoundsException or NullPointerException
        list.addAll(5, null);
    }
}
