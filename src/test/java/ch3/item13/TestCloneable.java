package ch3.item13;

import org.junit.Test;

public class TestCloneable {
    @Test
    public void testCloneable(){
        PhoneNumberCloneable phoneNumberCloneable = new PhoneNumberCloneable(707, 867, 5309);
        phoneNumberCloneable.clone();


    }
}
