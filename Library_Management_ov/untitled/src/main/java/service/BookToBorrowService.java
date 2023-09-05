package service;

import Utils.AppUtils;
import Utils.DateUltis;
import Utils.FileUtils;
import model.Book;
import model.BookToBorrow;
import model.LibraryCard;
import model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import Enum.*;
import view.BookView;

public class BookToBorrowService {
    static File folder = new File("data");
    static boolean a = folder.mkdir();
    public static final String linkDBBookToBorrow = "./data/bookToBorrow.txt";

    public static List<BookToBorrow> readData() {
        File userFile = new File(linkDBBookToBorrow);
        List<BookToBorrow> list = new ArrayList<>();
        String strLine = "";
        try {
            FileReader fileReader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataBookToBorrow = strLine.split(",");
                BookToBorrow bookToBorrow = new BookToBorrow(Long.parseLong(strDataBookToBorrow[0]), Long.parseLong(strDataBookToBorrow[1]), Long.parseLong(strDataBookToBorrow[2]), LocalDate.parse(strDataBookToBorrow[3]), LocalDate.parse(strDataBookToBorrow[4]), Boolean.parseBoolean(strDataBookToBorrow[5]));
                list.add(bookToBorrow);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ignored) {
        }
        return list;
    }

    public List<BookToBorrow> getAllData() {
        return readData();
    }

    public BookToBorrow getById(long id) {
        List<BookToBorrow> bookToBorrowList = readData();
        return bookToBorrowList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public void addElement(BookToBorrow bookToBorrow) {
        List<BookToBorrow> bookToBorrowList = readData();
        bookToBorrowList.add(bookToBorrow);
        FileUtils.writeData(bookToBorrowList, linkDBBookToBorrow);
    }

    public void deleteById(long id) {
        List<BookToBorrow> bookToBorrowList = readData();
        bookToBorrowList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(bookToBorrowList::remove);
        FileUtils.writeData(bookToBorrowList, linkDBBookToBorrow);
    }

    public void showData() {
        List<BookToBorrow> bookToBorrowsList = readData();
        if (bookToBorrowsList.isEmpty()) {
            System.out.println("Danh sách User đang trống, vui lòng thêm vào!!");
        } else {
            showBookToBorrowDetail(bookToBorrowsList);
        }
    }

    public void showBookToBorrowDetail(List<BookToBorrow> bookToBorrowsList) {
        System.out.println("                                                              LIST SÁCH MƯỢN");
        System.out.println("=================================================================================================================================================================================");
        System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-15s| %-12s| %-23s|\n", "ID", "Tên sách", "Người mượn", "SĐT", "Email", "Ngày Mượn", "Ngày Trả", "Trạng thái");
        for (BookToBorrow b : bookToBorrowsList) {
            UserService userService = new UserService();
            BookService bookService = new BookService();
            String bookStatus = "";
            if (b.isStatus()) {
                bookStatus = "Chưa quá hạn";
            } else bookStatus = "Quá hạn";
            System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-15s| %-12s| %-23s|\n", b.getId(), bookService.getById(b.getBookID()).getBookName(), userService.getById(b.getIdUser()).getUserName(), userService.getById(b.getIdUser()).getPhone(), userService.getById(b.getIdUser()).getEmail(), b.getBorrowDate(), b.getReturnDate(), bookStatus);
        }
    }

    public void findShow() {
        System.out.println("TÌM SÁCH MƯỢN: ");
        System.out.println("1: Tìm theo tên sách");
        System.out.println("2: Tìm theo tên người mượn");
        System.out.println("3: Tìm theo ngày mượn");
        System.out.println("4: Tìm theo trạng thái quá hạn");
        System.out.println("0: Quay lại");
    }

    public void findBookToBorrow(User user) {
        findShow();
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Bạn muốn tìm theo: "));
            if (select == 1) {
                String strToFind = AppUtils.typing("Nhập tên sách: ");
                BookService bookService = new BookService();
                List<Book> bookContStr = bookService.getAllData();
                List<Book> bookHasStr = bookContStr.stream().filter(s -> s.getBookName().contains(strToFind)).collect(Collectors.toList());
                List<BookToBorrow> bookToBorrowList = getAllData();
                List<BookToBorrow> bookToBorrowsListToNameBook = new ArrayList<>();
                for (Book b : bookHasStr) {
                    for (BookToBorrow bTB : bookToBorrowList) {
                        if (bTB.getBookID() == b.getId()) {
                            bookToBorrowsListToNameBook.add(bTB);
                        }
                    }
                }
                showBookToBorrowDetail(bookToBorrowsListToNameBook);
                findBookToBorrow(user);
            } else if (select == 2) {
                String strToFind = AppUtils.typing("Nhập tên người mượn: ");
                UserService userService = new UserService();
                List<User> userContStr = userService.getAllData();
                List<User> userHasStr = userContStr.stream().filter(s -> s.getUserName().contains(strToFind)).collect(Collectors.toList());
                List<BookToBorrow> bookToBorrowList = getAllData();
                List<BookToBorrow> bookToBorrowsListToName = new ArrayList<>();
                for (User u : userHasStr) {
                    for (BookToBorrow bTB : bookToBorrowList) {
                        if (bTB.getIdUser() == u.getId()) {
                            bookToBorrowsListToName.add(bTB);
                        }
                    }
                }
                showBookToBorrowDetail(bookToBorrowsListToName);
                findBookToBorrow(user);
            } else if (select == 3) {
                String strToFind = AppUtils.typing("Nhập ngày mượn: ");

                List<BookToBorrow> bookToBorrowList = getAllData();
                List<BookToBorrow> bookToBorrowsListToDate = bookToBorrowList.stream().filter(s -> s.getBorrowDate().toString().contains(strToFind)).collect(Collectors.toList());
                showBookToBorrowDetail(bookToBorrowsListToDate);
                findBookToBorrow(user);
            } else if (select == 4) {
                BookToBorrowService bookToBorrowService = new BookToBorrowService();
                List<BookToBorrow> bookToBorrowList = getAllData();
                int strInput = Integer.parseInt(AppUtils.typing("Bạn muốn tìm:  1: Sách chưa quá hạn      2: Sách đã quá hạn trả"));
                if (strInput == 1) {
                    showBookToBorrowDetail(bookToBorrowList.stream().filter(BookToBorrow::isStatus).collect(Collectors.toList()));
                    findBookToBorrow(user);
                } else if (strInput == 2) {
                    showBookToBorrowDetail(bookToBorrowList.stream().filter(s -> ! s.isStatus()).collect(Collectors.toList()));
                    findBookToBorrow(user);
                } else {
                    System.out.println("Giá trị bạn nhập không đúng");
                    BookView.bookSelect(user);
                }
            } else if (select == 0) {
                BookView.bookSelect(user);
            } else System.out.println("Bạn đã nhập không đúng, vui lòng nhập lại!!!");
        } while (select != 0);
    }
    public void extendCard(){
        LibraryCardService libraryCardService = new LibraryCardService();
        List<LibraryCard> libraryCardList = libraryCardService.getAllData();
        libraryCardService.showLibraryCard(libraryCardList);
        long id = Long.parseLong(AppUtils.typing("Nhập ID thẻ bạn muốn gia hạn"));
        Objects.requireNonNull(libraryCardList.stream().filter(s -> s.getId() == id).findFirst().orElse(null)).setCreateDate(LocalDate.now());
        libraryCardService.showLibraryCard(libraryCardList);
    }

    public void expiredManager(User user) {
        BookToBorrowService bookToBorrowService = new BookToBorrowService();
        List<BookToBorrow> bookToBorrowList = getAllData();
        List<BookToBorrow> bookToBorrowList1 = bookToBorrowList.stream().filter(s -> ! s.isStatus()).collect(Collectors.toList());
        System.out.println("                                                              QUẢN SÁCH MƯỢN QUÁ HẠN");
        System.out.println("=================================================================================================================================================================================");
        System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s|\n", "ID", "Tên sách", "Người mượn", "SĐT", "Ngày Mượn", "Ngày Trả", "Trạng thái", "Tiền Phạt");
        for (BookToBorrow b : bookToBorrowList1) {
            UserService userService = new UserService();
            BookService bookService = new BookService();
            String bookStatus = "";
            if (b.isStatus()) {
                bookStatus = "Chưa quá hạn";
            } else bookStatus = "Quá hạn";
            double payment = 0;
            if (b.getReturnDate() == null) {
                double date = DateUltis.getDateBetwen(LocalDate.now(), b.getBorrowDate());
            } else {
                double date = DateUltis.getDateBetwen(b.getReturnDate(), b.getBorrowDate());
                if (date > 30) {
                    payment = (date - 30) * 2000;
                } else payment = 0;
                System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s|\n", b.getId(), bookService.getById(b.getBookID()).getBookName(), userService.getById(b.getIdUser()).getUserName(), userService.getById(b.getIdUser()).getPhone(), b.getBorrowDate(), b.getReturnDate(), bookStatus, payment + " đồng");
                findBookToBorrow(user);
            }
        }
    }
}
