package view;

import Utils.AppUtils;
import model.User;
import service.UserService;
import Enum.*;

import java.util.ArrayList;
import java.util.List;

public class LoginView {
    public static void loginSystem(){
        User user;
        do {
            String account = AppUtils.typing("Mời nhập tên đăng nhập của bạn: ");
            String password = AppUtils.typing("Nhập mật khẩu: ");
        user = UserService.readData().stream().filter(s->s.getAccount().equals(account)&&s.getPassword().equals(password)).findFirst().orElse(null);
        }while (user == null);
        if(user.getRole().getName().equals("Đọc giả")){
            loginUser(user.getId());
        }else loginAdmin(user.getId());
    }
    public static void loginUser(long id) {
        List<User> userList = UserService.readData();
        User user = userList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (user != null) {
            ClientView.clientSelect(user);
        }
    }

    public static void loginAdmin(long id){
            List<User> userList = UserService.readData();
            User user = userList.stream().filter(s-> s.getId() == id).findFirst().orElse(null);
            if(user!=null){
                AdminView.adminSelect(user);
                System.out.println("Hello Admin");
            }
        }

    }

