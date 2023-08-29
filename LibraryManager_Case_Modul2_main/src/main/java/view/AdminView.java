package view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

import java.util.Scanner;
@Getter
@Setter
@NoArgsConstructor

public class AdminView {
    Scanner input = new Scanner(System.in);
    public User user;
    public AdminView(User user){
        this.user = user;
    }

}
