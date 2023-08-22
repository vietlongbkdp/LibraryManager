package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Enum.EGender;
import Enum.ERole;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String password;
    private int age;
    private String email;
    private EGender gender;
    private ERole role;

    public User(long id, String name, String password, int age, String email, EGender gender, ERole role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    @Override
    public String toString() {
        return this.getId() + ","+this.getName() + ","+ this.getPassword()+ ","+ this.getAge() + ","+this.getEmail() + ","+this.getGender().getName() + ","+ this.getRole().getRole()+ "\n";
    }

}

