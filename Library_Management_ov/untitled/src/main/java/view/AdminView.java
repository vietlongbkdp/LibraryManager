package view;

import Utils.AppUtils;
import model.User;

public class AdminView {
    public static void adminShow(User user){
        System.out.println("CHÀO MỪNG ADMIN "+user.getUserName()+" ĐÃ QUAY LẠI!");
        System.out.println("=============================MENU===========================");
        System.out.println("1: Quản lý sách");
        System.out.println("2: Quản lý User");
        System.out.println("3: Quản lý Danh mục mượn/trả");
        System.out.println("0: Quay lại");
    }
    public static void adminSelect(User user){
        adminShow(user);
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if(select == 1){
                BookView.bookSelect();
            }else if (select ==2){
                System.out.println("Hello 2");
            }else if (select == 0){
                LoginView.loginUser(user);
            }else if(select == 3){

            }else if(select!=0) System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
}
