package item11;

import item10.PhoneNumber;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestHashCode {

    @Test
    public void testHashcode(){
        Map<PhoneNumber, String> m = new HashMap<>();
        PhoneNumber phoneNumber = new PhoneNumber(707, 867, 5309);
        m.put(phoneNumber, "Jenny");
        Assert.assertNotNull(m.get(phoneNumber));
        Assert.assertNull(m.get(new PhoneNumber(707, 867, 5309)));


        Map<PhoneNumberHashCode, String> mapfix = new HashMap<>();
        PhoneNumberHashCode phoneNumberHashCode = new PhoneNumberHashCode(707, 867, 5309);
        mapfix.put(phoneNumberHashCode, "Jenny");
        Assert.assertNotNull(mapfix.get(phoneNumberHashCode));
        Assert.assertNotNull(mapfix.get(new PhoneNumberHashCode(707, 867, 5309)));


    }
}
