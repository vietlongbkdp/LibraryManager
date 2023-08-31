package Enum;

import lombok.Getter;

@Getter
public enum EPeriod {
    LOW("6 Tháng", 1),
    MEDIUM("12 Tháng",2),
    HIGH("24 Tháng",3);
    private String period;
    private  int id;
    EPeriod(String period, int id){
        this.period = period;
        this.id = id;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setPri(int id) {
        this.id = id;
    }
    public static EPeriod findById(int id) {
        for (EPeriod e : values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
