package Enum;

import lombok.Getter;
@Getter
public enum ERole {
    CLIENT("Người đọc",1),
    ADMIN("Quản trị viên",2);

    private String name;
    private int id;

    ERole(String name, int value) {
        this.name = name;
        this.id = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.id = value;
    }

    public static ERole findByname(String name) {
        for (ERole e : values()) {
            if ((e.getName().equals(name))) {
                return e;
            }
        }
        return null;
    }
}


