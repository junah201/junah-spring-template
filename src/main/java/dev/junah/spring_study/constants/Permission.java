package dev.junah.spring_study.constants;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum Permission {
    USER(0b0000),
    MANAGER(0b1000),
    ADMIN(0b1_0000);

    private final int bit;

    Permission(int bit) {
        this.bit = bit;
    }

    public static boolean hasPermission(int userPermissions, Permission permission) {
        return (userPermissions & permission.bit) == permission.bit;
    }

    public static List<Permission> fromBits(int bits) {
        List<Permission> result = new ArrayList<>();
        for (Permission p : Permission.values()) {
            if (hasPermission(bits, p)) {
                result.add(p);
            }
        }
        return result;
    }
}
