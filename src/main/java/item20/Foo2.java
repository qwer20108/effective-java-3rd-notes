package item20;

public interface Foo2 {
    void foo();
    default void bar(){
        System.out.print("bar2");
    }
}
