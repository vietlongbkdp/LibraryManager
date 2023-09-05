package view;
import Utils.AppUtils;
import model.User;
import service.BookService;
import service.BookToBorrowService;

public class BookToBorrowView {
    public static void bookToBorrowShow(){
        System.out.println("                    TRANG QUẢN LÝ DANH MỤC MƯỢN/TRẢ");
        System.out.println("====================================================================");
        System.out.println(" ╔═══════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                           ║");
        System.out.println(" ║                    1. TÌM KIẾM                            ║");
        System.out.println(" ║                    2. QUẢN LÝ SÁCH QUÁ HẠN                ║");
        System.out.println(" ║                    3. GIA HẠN THẺ THƯ VIỆN                ║");
        System.out.println(" ║                    4. HIỂN THỊ TẤT CẢ ĐƠN MƯỢN TRẢ        ║");
        System.out.println(" ║                    5. LẬP BÁO CÁO HOẠT ĐỘNG               ║");
        System.out.println(" ║                    0. QUAY LẠI                            ║");
        System.out.println(" ║                                                           ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════╝");
    }
    public static void borrowSelect(User user){
        bookToBorrowShow();
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if(select == 1){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                bookToBorrowService.findBookToBorrow(user);
            }else if (select ==2){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                bookToBorrowService.expiredManager(user);
            }else if (select ==3){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
//                bookToBorrowService.extendCard();
            }else if (select ==4){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                bookToBorrowService.showData();
            }else if (select ==5){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
//                bookToBorrowService.showReport(user);
            }else if (select == 0){
                AdminView.adminSelect(user);
            }else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
}
