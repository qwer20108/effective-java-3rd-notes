package item10;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
}
