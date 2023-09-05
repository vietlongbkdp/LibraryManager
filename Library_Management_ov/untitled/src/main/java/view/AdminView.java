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
        System.out.println("0: Đăng xuất");
    }
    public static void adminSelect(User user){
        adminShow(user);
        int select = 1000;
        do {
            try{
                select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            }catch (Exception exception){
                System.err.println("Vui lòng nhập số");
            }
            if(select == 1){
                BookView.bookSelect(user);
            }else if (select ==2){
                UserView.userSelect(user);
            }else if (select == 0){
                StartView.start();
            }else if(select == 3){
                BookToBorrowView.borrowSelect(user);
            }else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
}
