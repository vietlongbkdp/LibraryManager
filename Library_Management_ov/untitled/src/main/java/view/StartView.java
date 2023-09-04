package view;

import Utils.AppUtils;

import java.util.Scanner;

public class StartView {
    public static void menuStart() {
        System.out.println();
        System.out.println(" CHÀO MỪNG BẠN ĐẾN VỚI THƯ VIỆN CỦA LONG SIÊU CẤP VIP PRO");
        System.out.println();
        System.out.println(" ╔══════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                      ║");
        System.out.println(" ║                    1. ĐĂNG NHẬP                      ║");
        System.out.println(" ║                    2. ĐĂNG KÍ THÀNH VIÊN             ║");
        System.out.println(" ║                    0. THOÁT RA                       ║");
        System.out.println(" ║                                                      ║");
        System.out.println(" ╚══════════════════════════════════════════════════════╝");
    }
    public static void start(){
        menuStart();
        int select = 0;
            do {
                try {
                    select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
                }catch (NumberFormatException numberFormatException){
                    System.err.println("Vui lòng nhập đúng định dạng");
                }
                if(select == 1){
                LoginView.loginSystem();
                } else if (select == 2) {
                RegisterView.registerNewClient();
                }else if(select == 0){
                    System.out.println("Bạn đã thoát, Tạm biệt!!!");
                    System.exit(1);
                }else System.out.println("Bạn đã nhập sai rồi, Vui lòng nhập lại !");
            }while(true);
    }



}
