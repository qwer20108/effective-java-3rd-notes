package item14;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.TreeSet;

public class TestCompare {

    @Test
    public void testBigDecimal() {
        var bigDecimal = new BigDecimal("1.0");
        var bigDecimal2 = new BigDecimal("1.00");

        var bigDecimalHashSet = new HashSet<>();
        var bigDecimalTreeSet = new TreeSet<>();

        bigDecimalHashSet.add(bigDecimal);
        bigDecimalHashSet.add(bigDecimal2);
        Assert.assertEquals(2, bigDecimalHashSet.size());

        bigDecimalTreeSet.add(bigDecimal);
        bigDecimalTreeSet.add(bigDecimal2);
        Assert.assertEquals(1, bigDecimalTreeSet.size());

    }
}
