package view;

import Utils.AppUtils;
import model.Book;
import model.User;
import service.BookService;

import java.util.List;

public class BookView {
    public static void bookShow(){
        System.out.println();
        System.out.println("                        CHÀO MỪNG ADMIN                       ");
        System.out.println();
        System.out.println(" ╔═══════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                           ║");
        System.out.println(" ║                    1. TÌM SÁCH                            ║");
        System.out.println(" ║                    2. THÊM SÁCH VÀO THƯ VIỆN              ║");
        System.out.println(" ║                    3. CẬP NHẬT THÔNG TIN SÁCH             ║");
        System.out.println(" ║                    4. XOÁ SÁCH KHỎI THƯ VIỆN              ║");
        System.out.println(" ║                    5. HIỂN THỊ TẤT CẢ SÁCH                ║");
        System.out.println(" ║                    0. QUAY LẠI                            ║");
        System.out.println(" ║                                                           ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════╝");
    }
    public static void bookSelect(User user){
            bookShow();
            int select = 0;
            do {
                select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
                if(select == 1){
                    BookService bookService = new BookService();
                    bookService.findBook(user);
                }else if (select ==2){
                    BookService bookService = new BookService();
                    bookService.addElement(RegisterView.registerNewBook());
                    bookService.showData();
                }else if (select ==3){
                    updateBook();
                }else if (select ==4){
                    BookService bookService = new BookService();
                    bookService.showData();
                    long id = Long.parseLong(AppUtils.typing("Nhập Id sách bạn muốn xoá"));
                    String isSure = AppUtils.typing("Bạn có chắc chắn muốn xoá không ? (Y/N)");
                    if((isSure.equals("Y"))||isSure.equals("y")){
                        bookService.deleteById(id);
                        bookService.showData();
                    }else bookSelect(user);
                }else if (select ==5){
                    BookService bookService = new BookService();
                    bookService.showData();
                }else if (select == 0){
                    AdminView.adminSelect(user);
                }else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
            }while (select!=0);
    }
    public static void updateBook(){
        BookService bookService = new BookService();
        bookService.showData();
        long id = Long.parseLong(AppUtils.typing("Nhập ID sách bạn muốn cập nhật: "));
        bookService.updateById(id, RegisterView.registerNewBook());
    }
}
