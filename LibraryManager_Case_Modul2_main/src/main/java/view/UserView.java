package view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.LibraryCard;
import model.User;
import Enum.*;

import java.time.LocalDate;
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
    public void userSelection() {
            int index = 0;
//        LibraryCard libraryCard = new LibraryCard(user, 1000, "CS01", ETypeCard.STANDARD, LocalDate.of(2022,12, 30), LocalDate.of(2023, 12,30), EPrice.STANDARD);
        LibraryCard libraryCard = new LibraryCard();
        do {
            System.out.println("========Chào mừng bạn đến với thư viện của Long siêu cấp Vip Prồ=========");
            System.out.println("Menu: ");
            System.out.println("1: Thẻ thư viện của tôi");
            System.out.println("2: Tìm sách");
            System.out.println("3: Danh mục mượn, trả");
            index = Integer.parseInt(input.nextLine());
            if(index == 1){
                libraryCard.selectLibraryCard();
            } else if (index == 2) {
                System.out.println("hello 2");
            } else if (index == 3) {
                System.out.println("3");
            }else if(index!=0){
                System.out.println("Giá trị bạn nhập không đúng!!");
            }

        }while (index!=0);
        System.out.println("hello hihi");
    }
}
