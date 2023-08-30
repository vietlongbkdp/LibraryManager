package Enum;

import lombok.Getter;

@Getter
public enum EPrice {
    STANDARD(100000, 1),
    VIP(200000,2);
    private double price;
    private int id;
    EPrice(double price, int id){
        this.price = price;
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
    public static EPrice findByPrice(double price) {
        for (EPrice e : values()) {
            if ((e.getPrice() == price)) {
                return e;
            }
        }
        return null;
    }
}
