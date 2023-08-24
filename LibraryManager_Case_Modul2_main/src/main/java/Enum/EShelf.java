package Enum;

import lombok.Getter;
@Getter
public enum EShelf {
    A("Kệ A",1),
    B("Kệ B",2),
    C("Kệ C",3),
    D("Kệ D",4),
    E("Kệ E",5),
    F("Kệ F",6),
    G("Kệ G",7);

    private String name;
    private int id;

    EShelf(String name, int value) {
        this.name = name;
        this.id = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.id = value;
    }

    public static EShelf findByname(String name) {
        for (EShelf e : values()) {
            if ((e.getName().equals(name))) {
                return e;
            }
        }
        return null;
    }
}


