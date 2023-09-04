package service;

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
        } catch (IOException e) {
            System.err.println("LỖI TRONG QUÁ TRÌNH ĐỌC DỮ LIỆU");
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
        StringBuilder strShowData = new StringBuilder();
        for (Book u : bookList) {
            strShowData.append(u.toString());
        }
        System.out.println(strShowData);
    }
    }
    public void deleteById(long id) {
        List<Book> bookList = readData();
        bookList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(bookList::remove);
        FileUtils.writeData(bookList, linkDBBook);
    }
//    public void updateById(long id, Book book) {
//        List<Book> bookList = readData();
//        Book bookOld = bookList.stream().filter(bookItem -> bookItem.getId() == id).findFirst().orElse(null);
//        if (bookOld != null) {
//            bookOld.setId(book.getId());
//            bookOld.setBookName(book.getBookName());
//            bookOld.setAuthor(book.getAuthor());
//            bookOld.setPublisher(book.getPublisher());
//            bookOld.setYearPub(book.getYearPub());
//            bookOld.setStatus(book.isStatus());
//            bookOld.setShelf(book.getShelf());
//            bookOld.setQuantity(book.getQuantity());
//            bookOld.setETypeBook(book.getETypeBook());
//            bookOld.setPrice(book.getPrice());
//            bookOld.setDescription(book.getDescription());
//            bookOld.setDateAdd(book.getDateAdd());
//        }
//        FileUtils.writeData(bookList, linkDBBook);
//    }
}
