package view;

import model.ManagerUser;
import model.User;

import java.time.LocalDate;
import java.lang.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import Enum.EGender;
import Enum.ERole;
import service.UserService;

import  until.ValidateUntil;

import javax.jws.soap.SOAPBinding;

import static until.ValidateUntil.*;

public class Login {
    static Scanner input = new Scanner(System.in);

    public static void launcher() {
        try {
            System.out.println(" ================Chào mừng bạn đến với thư viện của Long Siêu cấp Vip Pro===================");
            int indexLogin;
            do {
                System.out.println("MENU");
                System.out.println("1: ĐĂNG NHẬP");
                System.out.println("2: ĐĂNG KÝ TÀI KHOẢN USER");
                System.out.println("0: THOÁT");
                System.out.println("Nhập lựa chọn của bạn :");
                indexLogin = Integer.parseInt(input.nextLine());
                if (indexLogin == 1) {
                    loginUser();
                } else if (indexLogin == 2) {
                    signinUser();
                } else if ((indexLogin != 0)) {
                    System.out.println("Bạn nhập sai rồi, mời nhập lại");
                }
            } while (indexLogin != 0);
            System.out.println("THANK YOU!!");
        } catch (NumberFormatException numberFormatException) {

        }

    }

    public static void signinUser() {
        try {
            UserService us1 = new UserService();
            System.out.println("=============ĐĂNG KÝ TÀI KHOẢN USER============");
            long inputID;
            do {
                boolean flag;
                do {
                    System.out.println("NHẬP ID: ");
                    inputID = Long.parseLong(input.nextLine());
                    flag = true;
                    List<User> listTemp = us1.getAllUser();
                    if (! listTemp.isEmpty()) {
                        for (User u : listTemp) {
                            if (u.getId() == inputID) {
                                flag = false;
                                System.out.println("ID NÀY ĐÃ TỒN TẠI, VUI LÒNG NHẬP LẠI");
                            }
                        }
                    }
                } while (! flag);
                if (! checkID(inputID)) {
                    System.err.println("ID Clinet gồm 4 chữ số");
                }
            } while (! checkID(inputID));

            String inputAccount;
            do {
                System.out.println("NHẬP TÊN ĐĂNG NHẬP: ");
                inputAccount = input.nextLine();
                if (! checkAccount(inputAccount)) {
                    System.err.println("Tên đăng nhập không được chấp nhận");
                }
            } while (! checkAccount(inputAccount));

            String inputName;
            do {
                System.out.println("NHẬP HỌ VÀ TÊN: ");
                inputName = input.nextLine();
                if (! checkName(inputName)) {
                    System.err.println("Bạn đã nhập tên không đúng");
                }
            } while (! checkName(inputName));


            String inputPhone;
            do {
                System.out.println("NHẬP SỐ ĐIỆN THOẠI: ");
                inputPhone = input.nextLine();
                if (! checkPhone(inputPhone)) {
                    System.err.println("Số điện thoại không tồn tại");
                }
            } while (! checkPhone(inputPhone));

            String inputAddress;
            do {
                System.out.println("NHẬP ĐỊA CHỈ: ");
                inputAddress = input.nextLine();
                if (! checkAddress(inputAddress)) {
                    System.err.println("Địa chỉ không hợp lệ, vui lòng nhập lại");
                }
            } while (! checkAddress(inputAddress));

            String strInputDoB;
            do {
                System.out.println("NHẬP NGÀY THÁNG NĂM SINH (Theo định dạng yyyy - mm - dd): ");
                strInputDoB = input.nextLine();
                if (! checkDoB(strInputDoB)) {
                    System.err.println("Vui lòng nhập ngày tháng đúng định dạng");
                }
            } while (! checkDoB(strInputDoB));
            LocalDate inputDoB = LocalDate.parse(strInputDoB);

            String inputEmail;
            do {
                System.out.println("NHẬP EMAIL: ");
                inputEmail = input.nextLine();
                if (! checkEmail(inputEmail)) {
                    System.err.println("Email không tồn tại");
                }
            } while (! checkEmail(inputEmail));

            String strInputGender;
            do {
                System.out.println("NHẬP GIỚI TÍNH:    1: NAM           2: NỮ         3: OTHER");
                strInputGender = input.nextLine();
                if (! checkGender(strInputGender)) {
                    System.err.println("Vui lòng chọn một trong 3 lựa chọn trên");
                }
            } while (! checkGender(strInputGender));
            long inputGenderTemp = Long.parseLong(strInputGender);
            EGender inputGender = EGender.findById(inputGenderTemp);
            String inputPassword;
            do {
                System.out.println("NHẬP MẬT KHẨU (Mật khẩu có ít nhất 8 ký tự, bao gồm chữ cái viết hoa, viết thuờng, số và ký tự đặc biệt):");
                inputPassword = input.nextLine();
                if (! checkPassword(inputPassword)) {
                    System.err.println("Vui long nhập mật khẩu đúng định dạng");
                }
            } while (! checkPassword(inputPassword));

            String inputRePassword = "";
            do {
                System.out.println("NHẬP LẠI MẬT KHẨU");
                inputRePassword = input.nextLine();
            } while (! inputPassword.equals(inputRePassword));
            User user = new User(inputID, inputAccount, inputPassword, inputName, inputPhone, inputAddress, inputDoB, inputEmail, inputGender, ERole.CLIENT);
            us1.addUser(user);
            System.out.println("TẠO TÀI KHOẢN THÀNH CÔNG!!!");
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Bạn phải nhập đúng định dạng");
        } catch (DateTimeParseException dateTimeParseException) {
            System.err.println("Bạn cần phải nhập ngày đúng định dạng như trên!!!");
        } catch (NullPointerException nullPointerException) {
            System.err.println("Lỗi danh sách");
        }
    }

    public static void loginUser() {
        UserService us2 = new UserService();
        List<User> userListCheckLogin = us2.getAllUser();
        boolean flag = false;
        do {
            System.out.println("=============ĐĂNG NHẬP============");
            System.out.println("TÊN ĐĂNG NHẬP: ");
            String inputAccount = input.nextLine();
            System.out.println("MẬT KHẨU: ");
            String inputPassword = input.nextLine();
            User userLogin = userListCheckLogin.stream().filter(s ->s.getAccount().equals(inputAccount)).findFirst().orElse(null);
            if(userLogin!=null && userLogin.getPassword().equals(inputPassword) && userLogin.getRole().getName().equals("Đọc giả")){
                UserView user = new UserView(userLogin);
                user.userSelection();
                flag = true;
            }else if (userLogin!=null && userLogin.getPassword().equals(inputPassword) && userLogin.getRole().getName().equals("Quản trị viên")) {
                AdminView admin = new AdminView(userLogin);
                flag = true;
            }else System.out.println("Tên đăng nhập hoặc mật khẩu không đúng, vui lòng đăng nhập lại!");
        } while (!flag) ;
            System.out.println("Hello");

    }
}
