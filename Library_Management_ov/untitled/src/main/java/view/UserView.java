package view;

import Utils.AppUtils;
import model.User;
import service.BookService;
import service.UserService;

public class UserView {
    public static void userShow(){
        System.out.println();
        System.out.println("                        CHÀO MỪNG ADMIN                       ");
        System.out.println();
        System.out.println(" ╔═══════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                           ║");
        System.out.println(" ║                    1. TÌM USER                            ║");
        System.out.println(" ║                    2. THÊM MỚI USER                       ║");
        System.out.println(" ║                    3. CHỈNH SỬA THÔNG USER                ║");
        System.out.println(" ║                    4. XOÁ USER                            ║");
        System.out.println(" ║                    5. HIỂN THỊ TẤT CẢ USER                ║");
        System.out.println(" ║                    6. CHỈ ĐỊNH QUẢN TRỊ VIÊN              ║");
        System.out.println(" ║                    7. XOÁ QUẢN TRỊ VIÊN                   ║");
        System.out.println(" ║                    0. QUAY LẠI                            ║");
        System.out.println(" ║                                                           ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════╝");
    }
    public static void userSelect(User user){
        userShow();
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if(select == 1){
                UserService userService = new UserService();
                userService.findUser(user);
            }else if (select ==2){
                UserService userService = new UserService();
                userService.addElement(RegisterView.registerNewUser());
                userService.showData();
            }else if (select ==3){
                System.out.println("HIHI ĐOẠN NI TƯƠNG TỰ EM TẠM ĐỂ LẠI");
            }else if (select ==4){
                UserService userService = new UserService();
                userService.showData();
                long id = Long.parseLong(AppUtils.typing("Nhập Id User bạn muốn xoá"));
                String isSure = AppUtils.typing("Bạn có chắc chắn muốn xoá không ? (Y/N)");
                if((isSure.equals("Y"))||isSure.equals("y")){
                    userService.deleteById(id);
                    userService.showData();
                }else userSelect(user);
            }else if (select ==5){
                UserService userService = new UserService();
                userService.showData();
            }else if (select ==6){
                UserService userService = new UserService();
                userService.showData();
                long id = Long.parseLong(AppUtils.typing("Nhập ID của User bạn muốn đặt làm Quản trị viên"));
                    userService.setAdmin(id);
                    userService.showData();
                    userSelect(user);
            }else if (select ==7){
                UserService userService = new UserService();
                userService.showData();
                long id = Long.parseLong(AppUtils.typing("Nhập ID của User bạn muốn xoá quyền Quản trị viên"));
                userService.removeAdmin(id);
                userService.showData();
                userSelect(user);
            }else if (select == 0){
                AdminView.adminSelect(user);
            }else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
}
