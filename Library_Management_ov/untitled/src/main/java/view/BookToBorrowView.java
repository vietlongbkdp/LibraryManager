package view;
import Utils.AppUtils;
import Utils.FileUtils;
import model.Book;
import model.BookToBorrow;
import model.LibraryCard;
import model.User;
import service.BookService;
import service.BookToBorrowService;
import service.LibraryCardService;
import service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.stream.Collectors;

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
                bookToBorrowService.extendCard();
            }else if (select ==4){
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                bookToBorrowService.showData();
            }else if (select ==5){
                reportLibrary(user);
            }else if (select == 0){
                AdminView.adminSelect(user);
            }else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
    public static void bookToBorrowClientShow(){
        System.out.println("                        DANH MỤC MƯỢN/TRẢ SÁCH");
        System.out.println("====================================================================");
        System.out.println(" ╔═══════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                           ║");
        System.out.println(" ║                    1. XEM DANH MỤC SÁCH                   ║");
        System.out.println(" ║                    2. MƯỢN THÊM SÁCH                      ║");
        System.out.println(" ║                    3. TRẢ SÁCH                            ║");
        System.out.println(" ║                    4. HIỂN THỊ DANH SÁCH ĐANG MƯỢN        ║");
        System.out.println(" ║                    0. QUAY LẠI                            ║");
        System.out.println(" ║                                                           ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════╝");
    }
    public static void bookToBorrowSelect(long id) {
        bookToBorrowClientShow();
        UserService userService = new UserService();
        List<User> userList = userService.getAllData();
        User user = userList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if (select == 1) {
                BookService bookService = new BookService();
                bookService.findBook(user);
            } else if (select == 2) {
                BookService bookService = new BookService();
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                List<BookToBorrow> bookToBorrowList = bookToBorrowService.getAllData();
                LibraryCardService libraryCardService = new LibraryCardService();
                List<LibraryCard> libraryCardList = libraryCardService.getAllData();
                if (libraryCardList.isEmpty()) {
                    System.out.println("Danh mục đang trống");
                } else {
                    LibraryCard libraryCard = libraryCardList.stream().filter(s -> s.getIdUser() == user.getId()).findFirst().orElse(null);
                    long timeBorrow = 0;
                    if (libraryCard.getTypeCard().getId() == 1) {
                        timeBorrow = 1;
                    } else timeBorrow = 2;
                    List<Book> bookList = bookService.getAllData();
                    List<Book> bookListToReadly = new ArrayList<>();
                    for (Book book : bookList) {
                        if (book.isStatus()) {
                            bookListToReadly.add(book);
                        }
                    }
                    bookService.showBookDetail(bookListToReadly);
                    long idBorrow = Long.parseLong(AppUtils.typing("Nhập ID sách bạn muốn mượn: "));
                    Book newBook = bookList.stream().filter(s -> s.getId() == idBorrow).findFirst().orElse(null);
                    if (newBook == null) {
                        System.out.println("ID sách không tồn tại!!");
                    } else {
                        BookToBorrow bookToBorrowNew = new BookToBorrow(newBook.getId(), newBook.getId(), user.getId(), LocalDate.now(), LocalDate.now().plusMonths(timeBorrow), true);
                        bookToBorrowService.addElement(bookToBorrowNew);
                        for (Book b : bookList) {
                            if (b.getId() == bookToBorrowNew.getBookID()) {
                                b.setStatus(false);
                            }
                        }
                        FileUtils.writeData(bookList, "./data/book.txt");
                    }

                    List<BookToBorrow> listShow = bookToBorrowService.getAllData().stream().filter(bookToBorrow -> bookToBorrow.getIdUser() == user.getId()).collect(Collectors.toList());
                    bookToBorrowService.showBookDetailClient(listShow);

                }
            } else if (select == 3) {
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                List<BookToBorrow> bookToBorrowList = bookToBorrowService.getAllData();
                if(bookToBorrowList.isEmpty()){
                    System.out.println("Danh sách đang rỗng");
                }else {
                List<BookToBorrow> bookToBorrowListShow = bookToBorrowList.stream().filter(bookToBorrow -> bookToBorrow.getIdUser() == user.getId()).collect(Collectors.toList());
                bookToBorrowService.showBookDetailClient(bookToBorrowListShow);
                BookService bookService = new BookService();
                List<Book> bookList = bookService.getAllData();
                long idReturn = Long.parseLong(AppUtils.typing("Nhập ID sách bạn muốn trả"));
                try {
                    for (BookToBorrow b : bookToBorrowList) {
                        if (b.getBookID() == idReturn) {
                            bookToBorrowList.remove(b);
                            for (Book book:bookList){
                                if(book.getId() == b.getBookID()){
                                    book.setStatus(true);
                                }
                            }
                        }
                    }
                }catch (ConcurrentModificationException ignored){}

                FileUtils.writeData(bookToBorrowList, "./data/bookToBorrow.txt");
                FileUtils.writeData(bookList, "./data/book.txt");
                bookToBorrowService.showBookDetailClient(bookToBorrowList);
                }

            } else if (select == 4) {
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                List<BookToBorrow> bookToBorrowList = bookToBorrowService.getAllData();
                if (bookToBorrowList.isEmpty()) {
                    System.out.println("Danh mục đang trống");
                } else {
                    List<BookToBorrow> bookToBorrowListShow = bookToBorrowList.stream().filter(bookToBorrow -> bookToBorrow.getIdUser() == user.getId()).collect(Collectors.toList());
                    bookToBorrowService.showBookDetailClient(bookToBorrowListShow);
                }
            } else if (select == 0) {
                bookToBorrowSelect(user.getId());
            } else System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        } while (select != 0);
    }
    public static void reportLibrary(User user){
        BookToBorrowService bookToBorrowService = new BookToBorrowService();
        List<BookToBorrow> bookToBorrowList = bookToBorrowService.getAllData();
        List<BookToBorrow> bookLater = bookToBorrowList.stream().filter(s-> !s.isStatus()).collect(Collectors.toList());
        UserService userService = new UserService();
        List<User> userList = userService.getAllData();
        List<User> usersManag = userList.stream().filter(user1 -> user1.getRole().getName().equals("Đọc giả")).collect(Collectors.toList());
        LibraryCardService libraryCardService = new LibraryCardService();
        List<LibraryCard> libraryCardList = libraryCardService.getAllData();
        BookService bookService = new BookService();
        List<Book> bookList = bookService.getAllData();
        System.out.println("                               CẬP NHÂT BÁO CÁO QUẢN LÝ                      ");
        System.out.println(" ===============================================================================");
        System.out.println(" ╔═════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                                             ║");
        System.out.printf(" ║   Ngày lập                          :    %30s     ║", LocalDate.now());
        System.out.println();
        System.out.printf(" ║   Số đọc giả đang quản lý           :    %30s     ║", usersManag.size());
        System.out.println();
        System.out.printf(" ║   Số đọc giả đã có thẻ thư viện     :    %30s     ║", libraryCardList.size());
        System.out.println();
        System.out.printf(" ║   Số đầu sách đang quản lý          :    %30s     ║", bookList.size());
        System.out.println();
        System.out.printf(" ║   Số sách đang cho mượn             :    %30s     ║", bookToBorrowList.size());
        System.out.println();
        System.out.printf(" ║   Số sách quá hạn trả               :    %30s     ║", bookLater.size());
        System.out.println();
        System.out.printf(" ║   Người lập báo cáo                 :    %30s     ║", user.getUserName());
        System.out.println();
        System.out.println(" ║                                                                             ║");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════════════╝");

     }
}
