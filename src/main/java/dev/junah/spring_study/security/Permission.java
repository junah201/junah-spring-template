package dev.junah.spring_study.security;

public enum Permission {
    USER(1), ADMIN(8);

    private final int bit;

    Permission(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public static boolean hasPermission(int target, Permission required) {
        return (target & required.getBit()) == required.getBit();
    }
}
