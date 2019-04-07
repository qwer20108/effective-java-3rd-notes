package ch3.item10;

import java.awt.*;
import java.util.Objects;

public class ColorPointComposition {
    private final Point point;
    private final Color color;

    public ColorPointComposition(int x, int y, Color color) {
        point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    /**
       * Returns the point-view of this color point.
       */
    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPointComposition)) return false;
        ColorPointComposition cp = (ColorPointComposition) o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
