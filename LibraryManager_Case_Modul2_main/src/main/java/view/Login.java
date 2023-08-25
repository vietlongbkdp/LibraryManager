package view;

import model.ManagerUser;
import model.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import Enum.EGender;
import Enum.ERole;
import service.UserService;

public class Login {
    static Scanner input = new Scanner(System.in);
    public static void launcher(){
        System.out.println(" ================Chào mừng bạn đến với thư viện của Long Siêu cấp Vip Pro===================");
        int indexLogin = 0;
        do{
            System.out.println("MENU");
            System.out.println("1: ĐĂNG NHẬP");
            System.out.println("2: ĐĂNG KÝ TÀI KHOẢN USER");
            System.out.println("0: THOÁT");
            System.out.println("Nhập lựa chọn của bạn :");
            indexLogin = Integer.parseInt(input.nextLine());
            if(indexLogin == 1){
                loginUser();
            } else if (indexLogin ==2) {
                signinUser();
            } else if ((indexLogin != 0)){
                System.out.println("Bạn nhập sai rồi, mời nhập lại");
            }
        }while (indexLogin !=0);
        System.out.println("THANK YOU!!");
    }
    public static void signinUser() {
        try {
            UserService us1 = new UserService();
            System.out.println("=============ĐĂNG KÝ TÀI KHOẢN USER============");
            long inputID;
            boolean flag;
            do {
                System.out.println("NHẬP ID: ");
                inputID = Long.parseLong(input.nextLine());
                flag = true;
                List<User> listTemp = us1.getAllUser();
                if(!listTemp.isEmpty()) {
                    for (User u : listTemp) {
                        if (u.getId() == inputID) {
                            flag = false;
                            System.out.println("ID NÀY ĐÃ TỒN TẠI, VUI LÒNG NHẬP LẠI");
                        }
                    }
                }
            } while (!flag);
            System.out.println("NHẬP TÊN ĐĂNG NHẬP: ");
            String inputAccount = input.nextLine();
            System.out.println("NHẬP HỌ VÀ TÊN: ");
            String inputUserName = input.nextLine();
            System.out.println("NHẬP SỐ ĐIỆN THOẠI: ");
            String inputPhone = input.nextLine();
            System.out.println("NHẬP ĐỊA CHỈ: ");
            String inputAddress = input.nextLine();
            System.out.println("NHẬP NGÀY THÁNG NĂM SINH (Theo định dạng yyyy - mm - dd): ");
            LocalDate inputDoB = LocalDate.parse(input.nextLine());
            System.out.println("NHẬP EMAIL: ");
            String inputEmail = input.nextLine();
            System.out.println("NHẬP GIỚI TÍNH:    1: NAM           2: NỮ         3: OTHER");
            int inputGenderTemp = Integer.parseInt(input.nextLine());
            EGender inputGender = EGender.findById(inputGenderTemp);
            String inputPassword = "";
            do {
                System.out.println("NHẬP MẬT KHẨU (Phải nhiều hơn 8 ký tự và có ít nhất 1 chữ số):");
                inputPassword = input.nextLine();
            } while (! checkPassword(inputPassword));
            String inputRePassword = "";
            do {
                System.out.println("NHẬP LẠI MẬT KHẨU");
                inputRePassword = input.nextLine();
            } while (! inputPassword.equals(inputRePassword));
            User user = new User(inputID, inputAccount, inputPassword, inputUserName, inputPhone, inputAddress, inputDoB, inputEmail, inputGender, ERole.CLIENT);
            us1.addUser(user);
            System.out.println("TẠO TÀI KHOẢN THÀNH CÔNG!!!");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Bạn phải nhập đúng định dạng");
        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Bạn cần phải nhập ngày đúng định dạng như trên!!!");
        }catch (NullPointerException nullPointerException){
            System.out.println("Ngoại lệ Null");
        }
    }
    public static  void loginUser(){
        System.out.println("=============ĐĂNG NHẬP============");
        System.out.println("TÊN ĐĂNG NHẬP: ");
        String inputUserName = input.nextLine();
        System.out.println("MẬT KHẨU: ");
        String inputPassword = input.nextLine();
    }
    public static boolean checkPassword(String password) {
        if (password.length() < 8) {
            return false;
        } else {
            char c;
            for (int i = 0; i < password.length(); i++) {
                c = password.charAt(i);
                if (!Character.isDigit(c)) {
                        return false;
                    }
                }
            }
        return true;
    }
}
