import model.User;
import service.UserService;
import Enum.EGender;
import Enum.ERole;
public class Main {
    public static void main(String[] args) {
        User user1;
        user1 = new User(1001, "Nguyễn Viết Long", "123@@", 27, "vietlongbkdp@gmail.com", EGender.MALE, ERole.ADMIN);
        User user2 = new User(1002, "Hàng Quốc Đạt", "124@@", 26, "vietlongbkdp@gmail.com", EGender.MALE, ERole.USER);
        UserService us1 = new UserService();
        us1.addUser(user1);
        us1.addUser(user2);
//        us1.deleteUser(1003);
//        us1.updateUser(1002, user1);
    }
}
