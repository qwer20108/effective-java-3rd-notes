package item10;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterPoint extends Point {
    private static final AtomicInteger counter = new AtomicInteger();

    public CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public static int numberCreated() {
        return counter.get();
    }
    // Broken - violates Liskov substitution principle
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) return false;
        Point p = (Point) o;
        return p.getX() == getX() && p.getY() == getY();
    }
}
