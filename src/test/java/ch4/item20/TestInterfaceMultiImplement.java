package ch4.item20;

import org.junit.Test;

public class TestInterfaceMultiImplement {
    @Test
    public void testFooImplement(){
        FooImplement fooImplement = new FooImplement();
        fooImplement.bar();
        fooImplement.foo();
    }

}
