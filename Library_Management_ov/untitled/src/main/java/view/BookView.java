package view;

import Utils.AppUtils;
import model.Book;
import model.User;
import service.BookService;

import java.util.List;

public class BookView {
    public static List<Book> bookList;
    public static void bookShow(){
        System.out.println();
        System.out.println("                        CHÀO MỪNG ADMIN                       ");
        System.out.println();
        System.out.println(" ╔═══════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                           ║");
        System.out.println(" ║                    1. TÌM SÁCH                            ║");
        System.out.println(" ║                    2. THÊM SÁCH VÀO THƯ VIỆN              ║");
        System.out.println(" ║                    3. CHỈNH SỬA THÔNG TIN SÁCH            ║");
        System.out.println(" ║                    4. XOÁ SÁCH KHỎI THƯ VIỆN              ║");
        System.out.println(" ║                    5. HIỂN THỊ TẤT CẢ SÁCH                ║");
        System.out.println(" ║                    0. THOÁT RA                            ║");
        System.out.println(" ║                                                           ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════╝");
    }
    public static void bookSelect(){
            bookShow();
            int select = 0;
            do {
                select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
                if(select == 1){

                }else if (select ==2){
                    System.out.println("Hello 2");
                }else if (select ==3){
                    BookService bookService = new BookService();
                    bookService.addElement();
                }else if (select ==4){
                    System.out.println("Hello 4");
                }else if (select ==5){
                    BookService bookService = new BookService();
                    bookService.showData();
                }else if (select == 0){
                    System.exit(1);
                }else if(select!=0) System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
            }while (select!=0);
    }
    public static Book newBook(){

        return book;
    }
}
