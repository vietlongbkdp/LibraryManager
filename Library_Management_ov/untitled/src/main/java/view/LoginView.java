package view;

import Utils.AppUtils;
import model.User;
import service.UserService;
import Enum.*;

public class LoginView {
    public static void loginSystem(){
        User user;
        do {
            String account = AppUtils.typing("Mời nhập tên đăng nhập của bạn: ");
            String password = AppUtils.typing("Nhập mật khẩu: ");
        user = UserService.readData().stream().filter(s->s.getAccount().equals(account)&&s.getPassword().equals(password)).findFirst().orElse(null);
        }while (user == null);
        if(user.getRole().getName().equals("Đọc giả")){
            loginUser(user);
        }else loginAdmin(user);
    }
    public static void loginUser(User user){
        ClientView.clientSelect(user);
}
    public static void loginAdmin(User user){
        AdminView.adminSelect(user);
        System.out.println("Hello Admin");
    }

}
