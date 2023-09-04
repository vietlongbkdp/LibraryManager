package view;
import Utils.AppUtils;
import Utils.ValidateUntil;
import model.User;
import service.BookService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;
import Enum.*;

public class RegisterView {
    public static void registerNewClient(){
        UserService userService = new UserService();
        long id = 0;
        boolean flag = false;
        do {
            try {
                flag = false;
                id = Long.parseLong(AppUtils.typing("Nhập ID: "));
            }catch (NumberFormatException numberFormatException){
                System.err.println("Bạn cần nhập số!");
            }catch (NullPointerException nullPointerException){
                System.err.println("Dữ liệu trống, vui lòng kiểm tra!!");
            }catch (IllegalArgumentException illegalArgumentException){};
        if(!ValidateUntil.checkID(id)) System.err.println("ID phải gồm 4 chữ số");
        if (userService.checkExist(id)){
                    System.err.println("Mời nhập lại!!");
            }else flag = true;
        }while (!ValidateUntil.checkID(id) || !flag);

        String account;
        do {
        account = AppUtils.typing("Nhập tên đăng nhập: ");
        }while (!ValidateUntil.checkAccount(account));

        String name;
        do {
        name = AppUtils.typing("Nhập họ và tên");
        }while (!ValidateUntil.checkName(name));

        String phone;
        do {
        phone = AppUtils.typing("Nhập số điện thoại: ");
        }while (!ValidateUntil.checkPhone(phone));

        String address;
        do {
        address = AppUtils.typing("Nhập địa chỉ: ");
        }while (!ValidateUntil.checkAddress(address));

        String strDoB;
        do {
        strDoB = AppUtils.typing("Nhập ngày sinh (Theo định dạng YY-MM-DD) : ");
        }while (!ValidateUntil.checkDoB(strDoB));
        LocalDate doD = LocalDate.parse(strDoB);

        String email;
        do {
        email = AppUtils.typing("Nhập Email");
        }while (!ValidateUntil.checkEmail(email));

        String strGender;
        do {
        strGender = AppUtils.typing("Vui lòng chọn giới tính :    1: Nam      2: Nữ     3: Khác");
        }while (!ValidateUntil.checkGender(strGender));
        int inputGender = Integer.parseInt(strGender);
        EGender gender = EGender.findById(inputGender);

        String password;
        String rePassword;
        do {
        password = AppUtils.typing("Tạo mật khẩu : ");
        rePassword = AppUtils.typing("Xác nhận lại mật khẩu: ");
        }while (!ValidateUntil.checkPassword(password) && !password.equals(rePassword));
        User newUser = new User(id, account, password, name, phone, address, doD, email, gender, ERole.CLIENT, false);
        userService.addElement(newUser);
        StartView.start();
    }
    public static void registerNewBook(){
        BookService bookService = new BookService();
        long id = 0;
        boolean flag = false;
        do {
            try {
                flag = false;
                id = Long.parseLong(AppUtils.typing("Nhập ID: "));
            }catch (NumberFormatException numberFormatException){
                System.err.println("Bạn cần nhập số!");
            }catch (NullPointerException nullPointerException){
                System.err.println("Dữ liệu trống, vui lòng kiểm tra!!");
            }catch (IllegalArgumentException illegalArgumentException){};
            if(!ValidateUntil.checkID(id)) System.err.println("ID phải gồm 4 chữ số");
            if (bookService.checkExist(id)){
                System.err.println("Mời nhập lại!!");
            }else flag = true;
        }while (!ValidateUntil.checkID(id) || !flag);

        String account;
        do {
            account = AppUtils.typing("Nhập tên đăng nhập: ");
        }while (!ValidateUntil.checkAccount(account));

        String name;
        do {
            name = AppUtils.typing("Nhập họ và tên");
        }while (!ValidateUntil.checkName(name));

        String phone;
        do {
            phone = AppUtils.typing("Nhập số điện thoại: ");
        }while (!ValidateUntil.checkPhone(phone));

        String address;
        do {
            address = AppUtils.typing("Nhập địa chỉ: ");
        }while (!ValidateUntil.checkAddress(address));

        String strDoB;
        do {
            strDoB = AppUtils.typing("Nhập ngày sinh (Theo định dạng YY-MM-DD) : ");
        }while (!ValidateUntil.checkDoB(strDoB));
        LocalDate doD = LocalDate.parse(strDoB);

        String email;
        do {
            email = AppUtils.typing("Nhập Email");
        }while (!ValidateUntil.checkEmail(email));

        String strGender;
        do {
            strGender = AppUtils.typing("Vui lòng chọn giới tính :    1: Nam      2: Nữ     3: Khác");
        }while (!ValidateUntil.checkGender(strGender));
        int inputGender = Integer.parseInt(strGender);
        EGender gender = EGender.findById(inputGender);

        String password;
        String rePassword;
        do {
            password = AppUtils.typing("Tạo mật khẩu : ");
            rePassword = AppUtils.typing("Xác nhận lại mật khẩu: ");
        }while (!ValidateUntil.checkPassword(password) && !password.equals(rePassword));
        User newUser = new User(id, account, password, name, phone, address, doD, email, gender, ERole.CLIENT, false);
        userService.addElement(newUser);
        StartView.start();

}
