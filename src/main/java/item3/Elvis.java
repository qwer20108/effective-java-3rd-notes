package item3;

public class Elvis{
    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
    }

    public void leaveTheBuilding() {
        System.out.println("public field");
    }
}
