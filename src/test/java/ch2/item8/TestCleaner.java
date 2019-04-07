package ch2.item8;

import org.junit.Test;

public class TestCleaner {

    @Test
    public void adultRoom(){
        try(Room adultRoom = new Room(7)) { // run clean after try block print message
            System.out.println("Goodbye");
        }
    }
    @Test
    public void teenagerRoom(){
        new Room(99); // run clean in gc no print clean message
        System.out.println("Peace out");
//        System.gc();
//        for (int i = 0 ; i < Integer.MAX_VALUE; i++); //拖時間用
    }
}
