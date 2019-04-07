package ch2.item3;

import org.junit.Test;

public class TestElvis {
    @Test
    public void ElvisTest(){
        Elvis.INSTANCE.leaveTheBuilding();
    }

    @Test
    public void Elvis2Test() {
        Elvis2.getInstance().leaveTheBuilding();
    }

    @Test
    public void ElvisEnumTest() {
        ElvisEnum.INSTANCE.leaveTheBuilding();
    }

}
