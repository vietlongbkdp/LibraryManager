package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Enum.EGender;
import Enum.ERole;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {
    protected long id;
    protected String account;
    protected String password;
    protected String userName;
    protected String phone;
    protected String address;
    protected LocalDate doB;
    protected String email;
    protected EGender gender;
    protected ERole role;

    public User(long id, String account, String password, String userName, String phone, String address, LocalDate doB, String email, EGender gender, ERole role) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.doB = doB;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getAccount() + "," + this.getPassword()
                + "," + this.getUserName() + "," + this.getPhone() + ","
                + this.getAddress() + "," + this.getDoB() + "," + this.getEmail() + "," + this.getGender().getName()
                + "," + this.getRole().getName()+ "\n";
    }

}