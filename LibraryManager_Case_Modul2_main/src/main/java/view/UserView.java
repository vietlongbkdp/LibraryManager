package view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;
@Getter
@Setter
@NoArgsConstructor

public class UserView {
    Scanner input = new Scanner(System.in);
    public User user;
public UserView(User user){
    this.user = user;
}
    public void showSelection(){
        System.out.println("========Chào mừng bạn đến với thư viện của Long siêu cấp Vip Prồ=========");
        System.out.println("Menu: ");
        System.out.println("1: Thẻ thư viện của tôi");
        System.out.println("2: Danh sách ");
    }
}
