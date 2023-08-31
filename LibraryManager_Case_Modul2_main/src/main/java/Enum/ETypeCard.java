package Enum;

import lombok.Getter;
@Getter
public enum ETypeCard {
    STANDARD("Tiêu chuẩn",1),
    VIP("VIP",2);
    private String name;
    private int id;
    ETypeCard(String name, int value) {
        this.name = name;
        this.id = value;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.id = value;
    }

    public static ETypeCard findById(int id) {
        for (ETypeCard e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

}
