package item18;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCompositionOverInheritance {
    @Test
    public void testInstrumentedHashSet(){
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("Snap", "Crackle", "Pop"));
        Assert.assertEquals(3, s.getAddCount());
    }
}
