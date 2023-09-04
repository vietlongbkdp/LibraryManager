package view;

import Utils.AppUtils;
import model.User;
import service.LibraryCardService;

public class ClientView {
    public static void clientShow(User user){
        System.out.println("CHÀO MỪNG "+user.getUserName()+" ĐẾN VỚI THƯ VIỆN CỦA LONG SIÊU CẤP VIP PRO");
        System.out.println("=============================MENU===========================");
        System.out.println("1: Thẻ thư viện của tôi");
        System.out.println("2: Quản lý danh mục mượn/trả sách");
        System.out.println("0: Quay lại");
    }
    public static void clientSelect(User user){
        clientShow(user);
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if(select == 1){
                LibraryCardView.libraryCardSelect(user);
            }else if (select ==2){
                System.out.println("Hello 2");
            }else if (select == 0){
                LoginView.loginUser(user);
            }else if(select!=0) System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
}
