package Enum;

import lombok.Getter;

@Getter

public enum EGender {
    FEMALE("Nữ", 2),
    MALE("Nam", 1),
    OTHER("Khác", 3);
    private String name;
    private int value;
    EGender(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
