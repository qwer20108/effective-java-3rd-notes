package ch6.Item36;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public class ChmodPermissionEnumSet {
    public enum Permission {
        READ("R"), WRITE("W"),EXECUTE("X");

        private final String permission;

        Permission(String permission){
            this.permission = permission;
        }

        public String getPermission() {
            return permission;
        }

        @Override
        public String toString() {
            return permission;
        }
    }

    public static void main(String[] args) {
        for (Permission permission : EnumSet.of(Permission.EXECUTE, Permission.WRITE, Permission.READ)) {
            System.out.print(permission);
        }
        System.out.println();

    }
}
