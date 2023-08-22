package Enum;

import lombok.Getter;

@Getter
public enum ERole {
    ADMIN("Admin", 1),
    USER("User", 2),
    VIEWER("Viewer",3);

    private String role;
    private int value;
   ERole(String role, int value){
       this.role = role;
       this.value = value;
   }

    public void setRole(String role) {
        this.role = role;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public static ERole findByRole(String role) {
        for (ERole e : values()) {
            if ((e.getRole().equals(role))){
                return e;
            }
        }
        return null;
    }
}
