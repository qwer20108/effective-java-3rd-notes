package ch4.item20;

public interface Foo {
    void foo();
    default void bar(){

        System.out.print("bar");
    }


}
