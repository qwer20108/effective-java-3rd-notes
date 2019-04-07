package ch2.item6;

public class BoxedPrimitive {
    public static long sumBoxed() {
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) sum += i;
        return sum;
    }

    public static long sum() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) sum += i;
        return sum;
    }

}


