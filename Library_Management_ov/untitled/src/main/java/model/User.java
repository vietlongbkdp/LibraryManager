package model;


import Enum.EGender;
import Enum.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User{
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
    protected boolean hasCard;

    public User(long id, String account, String password, String userName, String phone, String address, LocalDate doB, String email, EGender gender, ERole role, boolean hasCard) {
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
        this.hasCard = hasCard;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getAccount() + "," + this.getPassword()
                + "," + this.getUserName() + "," + this.getPhone() + ","
                + this.getAddress() + "," + this.getDoB() + "," + this.getEmail() + "," + this.getGender().getName()
                + "," + this.getRole().getName()+ ","+this.isHasCard()+"\n";
    }

}