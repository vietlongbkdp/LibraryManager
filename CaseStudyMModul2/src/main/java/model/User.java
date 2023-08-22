package model;

import Enum.EGender;
import Enum.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private String password;
    private int age;
    private String email;
    private EGender gender;
    private ERole role;

    @Override
    public String toString() {
        return this.getId() + ","+this.getName() + ","+ this.getPassword()+ ","+ this.getAge() + ","+this.getEmail() + ","+this.getGender().getName() + ","+ this.getRole().getRole() + "\n";
    }
}

