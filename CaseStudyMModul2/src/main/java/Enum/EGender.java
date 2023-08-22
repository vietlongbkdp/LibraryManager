package Enum;

import lombok.Getter;

@Getter

public enum EGender {
    FEMALE("Nữ", 2),
    MALE("Nam", 1),
    OTHER("Khác", 3);
    private String name;
    private int id;
    EGender(String name, int value) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.id = id;
    }
    public static EGender findByname(String name) {
        for (EGender e : values()) {
            if ((e.getName().equals(name))){
                return e;
            }
        }
        return null;
    }

}
