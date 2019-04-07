package ch3.item10;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestEquals {

    @Test
    public void testCaseInsensitiveString(){
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
        String s = "polish";

        Assert.assertFalse(s.equals(cis));
        Assert.assertTrue(cis.equals(s));


        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);
        Assert.assertFalse(list.contains(s));
    }


    @Test
    public void testCaseInsensitiveStringFix() {
        CaseInsensitiveStringRefactor cis = new CaseInsensitiveStringRefactor("Polish");
        String s = "Polish";

        Assert.assertFalse(s.equals(cis));
        Assert.assertFalse(cis.equals(s));


        List<CaseInsensitiveStringRefactor> list = new ArrayList<>();
        list.add(cis);
        Assert.assertFalse(list.contains(s));
    }

    @Test
    public void testColorPoint() {
        ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
        Assert.assertTrue(p1.equals(p2));
        Assert.assertTrue(p2.equals(p3));
        Assert.assertFalse(p1.equals(p3));

    }

    @Test
    public void testColorPointComposition() {
        ColorPointComposition p1 = new ColorPointComposition(1, 2, Color.RED);
        Point p2 = new Point(1, 2);
        ColorPointComposition p3 = new ColorPointComposition(1, 2, Color.BLUE);
        Assert.assertFalse(p1.equals(p2));
        Assert.assertFalse(p2.equals(p3));
        Assert.assertFalse(p1.equals(p3));

    }
    // Initialize unitCircle to contain all Points on the unit circle
    private static final Set<Point> unitCircle = Set.of(
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1));

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }
    @Test
    public void testunitCircle() {
        // 違反 Liskov substitution principle 子類別使用其繼承類別之方法與屬性應該運作良好
        Assert.assertFalse(onUnitCircle(new CounterPoint(1, 0)));

    }



}
