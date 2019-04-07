package ch4.item20;

public interface Foo3 extends Foo, Foo2 {
    @Override
    default void bar() {

    }
}
