package view;
import Utils.AppUtils;
import Utils.PasswordUtils;
import Utils.ValidateUntil;
import model.Book;
import model.User;
import service.BookService;
import service.UserService;

import java.time.LocalDate;
import Enum.*;

public class RegisterView {
    public static void registerNewClient() {
        User user = registerNewUser();
        UserService userService = new UserService();
        userService.addElement(user);
        StartView.start();
    }

    public static User registerNewUser() {
        UserService userService = new UserService();
        long id = 0;
        boolean flag = false;
        do {
            try {
                flag = false;
                id = Long.parseLong(AppUtils.typing("Nhập ID: "));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Bạn cần nhập số!");
            } catch (NullPointerException nullPointerException) {
                System.err.println("Dữ liệu trống, vui lòng kiểm tra!!");
            } catch (IllegalArgumentException ignored) {
            }
            ;
            if (!ValidateUntil.checkID(id)) System.err.println("ID phải gồm 4 chữ số");
            if (userService.checkExist(id)) {
                System.err.println("Mời nhập lại!!");
            } else flag = true;
        } while (!ValidateUntil.checkID(id) || !flag);

        String account;
        do {
            account = AppUtils.typing("Nhập tên đăng nhập: ");
        } while (!ValidateUntil.checkAccount(account));

        String name;
        do {
            name = AppUtils.typing("Nhập họ và tên");
        } while (!ValidateUntil.checkName(name));

        String phone;
        do {
            phone = AppUtils.typing("Nhập số điện thoại: ");
        } while (!ValidateUntil.checkPhone(phone));

        String address;
        do {
            address = AppUtils.typing("Nhập địa chỉ: ");
        } while (!ValidateUntil.checkAddress(address));

        String strDoB;
        do {
            strDoB = AppUtils.typing("Nhập ngày sinh (Theo định dạng YY-MM-DD) : ");
        } while (!ValidateUntil.checkDoB(strDoB));
        LocalDate doD = LocalDate.parse(strDoB);

        String email;
        do {
            email = AppUtils.typing("Nhập Email");
        } while (!ValidateUntil.checkEmail(email));

        String strGender;
        do {
            strGender = AppUtils.typing("Vui lòng chọn giới tính :    1: Nam      2: Nữ     3: Khác");
        } while (!ValidateUntil.checkGender(strGender));
        int inputGender = Integer.parseInt(strGender);
        EGender gender = EGender.findById(inputGender);

        String password;
        String rePassword;
        do {
            password = AppUtils.typing("Tạo mật khẩu : ");
            rePassword = AppUtils.typing("Xác nhận lại mật khẩu: ");
        } while (!ValidateUntil.checkPassword(password) && !password.equals(rePassword));
        String strPassword = PasswordUtils.generatePassword(password);
        return new User(id, account, strPassword, name, phone, address, doD, email, gender, ERole.CLIENT, false);
    }
    public static Book registerNewBook() {
        BookService bookService = new BookService();
        long id = 0;
        boolean flag = false;
        do {
            try {
                flag = false;
                id = Long.parseLong(AppUtils.typing("Nhập ID sách: "));
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Bạn cần nhập số!");
            } catch (NullPointerException nullPointerException) {
                System.err.println("Dữ liệu trống, vui lòng kiểm tra!!");
            } catch (IllegalArgumentException ignored) {
            }
            ;
            if (! ValidateUntil.checkID(id)) System.err.println("ID phải gồm 4 chữ số");
            if (bookService.checkExist(id)) {
                System.err.println("Mời nhập lại!!");
            } else flag = true;
        } while (! ValidateUntil.checkID(id) || ! flag);

        String bookName;
        do {
            bookName = AppUtils.typing("Nhập tên sách: ");
        } while (! ValidateUntil.checkBookName(bookName));

        String author;
        do {
            author = AppUtils.typing("Nhập họ và tên tác giả: ");
        } while (! ValidateUntil.checkAuthor(author));

        String publisher;
        do {
            publisher = AppUtils.typing("Nhập tên nhà xuất bản: ");
        } while (! ValidateUntil.checkName(publisher));

        String strShefl;
        do {
            strShefl = AppUtils.typing("Bạn muốn xếp lên kệ nào (A - G): ");
        } while (! ValidateUntil.checkShelf(strShefl));
        EShelf shelf = EShelf.findByname(strShefl);

        String strTypeBook;
        do {
            strTypeBook = AppUtils.typing("Chọn thể loại : 1: Tiểu thuyết   2: Truyện ngắn   3: Kiến thức khoa học   4: Sức khoẻ và thể thao ");
        } while (! ValidateUntil.checkTypeBook(strTypeBook));
        int inputTypeBook = Integer.parseInt(strTypeBook);
        ETypeBook typeBook = ETypeBook.findByID(inputTypeBook);

        String strPrice;
        do {
            strPrice = AppUtils.typing("Nhập giá trị cuốn sách : ");
        } while (! ValidateUntil.checkPrice(strPrice));
        double price = Double.parseDouble(strPrice);

        String description = AppUtils.typing("Nhập mô tả: ");

        return new Book(id, bookName, author, publisher, true, shelf, typeBook, price, description, LocalDate.now());
    }

}
