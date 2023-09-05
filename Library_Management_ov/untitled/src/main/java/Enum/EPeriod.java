package Enum;

import lombok.Getter;

@Getter
public enum EPeriod {
    LOW("6 Tháng", 1),
    MEDIUM("12 Tháng",2),
    HIGH("24 Tháng",3);
    private String periodr;
    private  int id;
    EPeriod(String periodr, int id){
        this.periodr = periodr;
        this.id = id;
    }

    public void setPeriod(String periodr) {
        this.periodr = periodr;
    }

    public void setPri(int id) {
        this.id = id;
    }
        public static EPeriod findByPeriod(String string) {
        for (EPeriod e : values()) {
            if (e.getPeriodr().equals(string)){
                return e;
            }
        }
        return null;
    }
    public static EPeriod findById(long id) {
        for (EPeriod ePeriod : values()) {
            if (ePeriod.getId() == id){
                return ePeriod;
            }
        }
        return null;
    }
}
