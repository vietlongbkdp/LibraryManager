package view;

import Utils.AppUtils;
import Utils.PasswordUtils;
import model.User;
import service.UserService;
import java.util.List;

public class LoginView {
    public static void loginSystem(){
        User user;
        do {
            String account = AppUtils.typing("Mời nhập tên đăng nhập của bạn: ");
            String password = AppUtils.typing("Nhập mật khẩu: ");

        user = UserService.readData().stream().filter(s->s.getAccount().equals(account)&& PasswordUtils.isValid(password, s.getPassword())).findFirst().orElse(null);
        if(user == null) System.err.println("Tên đăng nhập hoặc mật khẩu không đúng, vui lòng nhập lại");
        }while (user == null);
        if(user.getRole().getName().equals("Đọc giả")){
            loginUser(user.getId());
        }else loginAdmin(user.getId());
    }
    public static void loginUser(long id) {
        List<User> userList = UserService.readData();
        userList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(ClientView::clientSelect);
    }

    public static void loginAdmin(long id){
            List<User> userList = UserService.readData();
        userList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(AdminView::adminSelect);
    }

    }

