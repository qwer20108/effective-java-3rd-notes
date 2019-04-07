package ch4.item20;

public class FooImplement implements Foo, Foo2{
    @Override
    public void foo() {
        System.out.print("foo");
    }

    @Override
    public void bar() {
        System.out.print("bar");
    }
}
