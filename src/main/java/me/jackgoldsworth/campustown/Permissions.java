package me.jackgoldsworth.campustown;

public enum Permissions {
    PREFIX("ct.prefix");

    private String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
