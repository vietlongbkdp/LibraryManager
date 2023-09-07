package service;

import Utils.AppUtils;
import Utils.FileUtils;
import model.Book;
import model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.*;
import view.BookToBorrowView;
import view.BookView;
import view.ClientView;

public class BookService {
    static File folder = new File("data");
    static boolean a = folder.mkdir();
    public static final String linkDBBook = "./data/book.txt";
    public Book getById(long id) {
        List<Book> bookList = readData();
        return bookList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }
    public List<Book> getAllData() {
        return readData();
    }
    public void addElement(Book book) {
        List<Book> bookList = readData();
        bookList.add(book);
        FileUtils.writeData(bookList, linkDBBook);
    }
    public static List<Book> readData() {
        File bookFile = new File("./data/book.txt");
        List<Book> list = new ArrayList<>();
        String strLine = "";
        try {
            FileReader fileReader = new FileReader(bookFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataBook = strLine.split(",");
                Book book = new Book(Long.parseLong(strDataBook[0]), strDataBook[1],
                        strDataBook[2], strDataBook[3], Boolean.parseBoolean(strDataBook[4]), EShelf.findByname(strDataBook[5]), ETypeBook.findByname(strDataBook[6]),
                        Double.parseDouble(strDataBook[7]), strDataBook[8], LocalDate.parse(strDataBook[9]));
                list.add(book);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException | ArrayIndexOutOfBoundsException ignored) {
        }
        return list;
    }
    public boolean checkExist(long id) {
        List<Book> list = getAllData();
        if (list.isEmpty()) return false;
        Book bookCheck = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (bookCheck == null) {
            return false;
        } else return true;
    }

    public void showData() {
        List<Book> bookList = readData();
        if(bookList.isEmpty()){
            System.out.println("Thư viện đang trống, vui lòng thêm sách vào!!");
        }else {
            showBookDetail(bookList);
        }
            }
    public void showBookDetail(List<Book> list){
        System.out.println("                                                              Sách hiện có ");
        System.out.println("============================================================================================================================================================");
        System.out.printf("|%-4s| %-20s| %-12s| %-20s| %-15s| %-12s| %-20s| %-15s| %-20s| %-15s|\n", "ID", "Tên sách", "Tác giả", "Nhà xuất bản", "Trạng thái", "Kệ", "Thể loại", "Giá", "Mô tả", "Ngày thêm");
        for (Book u : list) {
            String bookStatus ="";
            if(u.isStatus()){
                bookStatus = "Sẵn sàng";
            }else bookStatus = "Đang cho mượn";
            System.out.printf("|%-4s| %-20s| %-12s| %-20s| %-15s| %-12s| %-20s| %-15s| %-20s| %-15s|\n", u.getId(), u.getBookName(), u.getAuthor(), u.getPublisher(), bookStatus, u.getShelf(), u.getETypeBook().getName(), u.getPrice(), u.getDescription(), u.getDateAdd());
        }
    }

    public void deleteById(long id) {
        List<Book> bookList = readData();
        bookList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(bookList::remove);
        FileUtils.writeData(bookList, linkDBBook);
    }
    public void findShow(){
        System.out.println("TÌM SÁCH: ");
        System.out.println("1: Tìm theo tên sách");
        System.out.println("2: Tìm theo kệ");
        System.out.println("3: Tìm theo thể loại");
        System.out.println("4: Sách chưa được mượn");
        System.out.println("0: Quay lại");
    }
    public void findBook(User user){
        findShow();
        int select =0;
        do {
            select = Integer.parseInt(AppUtils.typing("Bạn muốn tìm sách theo: "));
            if(select == 1){
                findBookByName();
                findBook(user);
            }else if(select ==2){
                findBookByShelf();
                findBook(user);
            } else if (select==3) {
                findBookByType();
                findBook(user);
            } else if (select==4) {
                findBookByStatus();
                findBook(user);
            }else if (select==0){
                if(user.getRole().getName().equals("Quản trị viên")){
                BookView.bookSelect(user);
                }else BookToBorrowView.bookToBorrowSelect(user.getId());
            }else System.out.println("Bạn đã nhập không đúng, vui lòng nhập lại!!!");
        }while (select!=0);

    }
    public void updateById(long id, Book book) {
        BookService bookService = new BookService();
        List<Book> bookList = bookService.getAllData();
        for (Book b:bookList) {
            if(b.getId() == id){
                b.setId(book.getId());
                b.setBookName(book.getBookName());
                b.setAuthor(book.getAuthor());
                b.setPublisher(book.getPublisher());
                b.setStatus(book.isStatus());
                b.setShelf(book.getShelf());
                b.setETypeBook(book.getETypeBook());
                b.setPrice(book.getPrice());
                b.setDescription(book.getDescription());
                b.setDateAdd(book.getDateAdd());
            }
        }
        FileUtils.writeData(bookList, linkDBBook);
    }
    public void findBookByName() {
        String strToFind = AppUtils.typing("Nhập tên sách bạn muốn tìm");
        List<Book> bookList = getAllData();
        if (bookList.isEmpty()) {
            System.out.println("Danh mục sách đang trống");
        } else {
            List<Book> bookListToName = new ArrayList<>();
            for (Book book : bookList) {
                if (book.getBookName().contains(strToFind)) {
                    bookListToName.add(book);
                }
            }
            showBookDetail(bookListToName);
        }
    }
    public void findBookByShelf() {
        String strToFind = AppUtils.typing("Nhập kệ sách bạn muốn tìm:  A    B    C    D    E   F   G");
        List<Book> bookList = getAllData();
        if (bookList.isEmpty()) {
            System.out.println("Danh mục sách đang trống");
        } else {

            List<Book> bookListToShelf = new ArrayList<>();
            for (Book book : bookList) {
                if (book.getShelf().getName().contains(strToFind)) {
                    bookListToShelf.add(book);
                }
            }
            showBookDetail(bookListToShelf);
        }
    }

    public void findBookByType() {
        int strToFind = Integer.parseInt(AppUtils.typing("Nhập thể loại bạn muốn tìm: 1: Tiểu thuyết    2: Truyện ngắn    3: Kiến thức khoa học    4: Sức khoẻ và thể thao"));
        List<Book> bookList = getAllData();
        if (bookList.isEmpty()) {
            System.out.println("Danh mục sách đang trống");
        } else {
            List<Book> bookListToType = new ArrayList<>();
            for (Book book : bookList) {
                if (book.getETypeBook().getId() == strToFind) {
                    bookListToType.add(book);
                }
            }
            showBookDetail(bookListToType);
        }
    }
    public void findBookByStatus() {
        List<Book> bookList = getAllData();
        if (bookList.isEmpty()) {
            System.out.println("Danh mục sách đang trống");
        } else {
            List<Book> bookListToReadly = new ArrayList<>();
            for (Book book : bookList) {
                if (book.isStatus()) {
                    bookListToReadly.add(book);
                }
            }
            showBookDetail(bookListToReadly);
        }
    }
}
