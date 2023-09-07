package service;
import Utils.DateUltis;
import Utils.FileUtils;
import model.LibraryCard;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.*;
import model.User;

import java.lang.*;


public class LibraryCardService{
    static File folder = new File("data");
    static boolean a = folder.mkdir();
    public static final String linkDBLibraryCard = "./data/libraryCard.txt";

    public Object getById(long id) {
        List<LibraryCard> libraryCardList = readData();
        return libraryCardList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<LibraryCard> getAllData() {
        return readData();
    }

    public void addElement(LibraryCard libraryCard) {
        List<LibraryCard> libraryCardList = readData();
        libraryCardList.add(libraryCard);
        FileUtils.writeData(libraryCardList, linkDBLibraryCard);
    }


    public void updateById(long id, LibraryCard libraryCard) {
        List<LibraryCard> libraryCardList = readData();
        LibraryCard libraryCardOld = libraryCardList.stream().filter(libraryCardItem -> libraryCardItem.getId() == id).findFirst().orElse(null);
        if (libraryCardOld != null) {
            libraryCardOld.setId(libraryCard.getId());
            libraryCardOld.setIdUser(libraryCard.getIdUser());
            libraryCardOld.setTypeCard(libraryCard.getTypeCard());
            libraryCardOld.setCreateDate(libraryCard.getCreateDate());
            libraryCardOld.setPeriod(libraryCard.getPeriod());
        }
        FileUtils.writeData(libraryCardList, linkDBLibraryCard);
    }


    public void deleteById(long id) {
        List<LibraryCard> libraryCardList = readData();
        libraryCardList.remove(libraryCardList.stream().filter(s -> s.getId() == id).findFirst().orElse(null));
        FileUtils.writeData(libraryCardList, linkDBLibraryCard);
    }


    public void showData() {
        List<LibraryCard> libraryCardList = readData();
        StringBuilder strShowData = new StringBuilder();
        for (LibraryCard u : libraryCardList) {
            strShowData.append(u.toString());
        }
        System.out.println(strShowData);
    }
    public void showLibraryCard(List<LibraryCard> list){
        UserService userService = new UserService();
        List<User> userList = userService.getAllData();
        System.out.println("                                                              Danh sách thẻ thư viện ");
        System.out.println("=================================================================================================================================================================================");
        System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-20s| %-20s| %-23s|\n", "ID", "Tên đọc giả", "SĐT", "Hạng thẻ", "Ngày tạo", "Ngày hết hạn","Trạng thái", "Phí gia hạn");
        for (LibraryCard libraryCard : list) {
            User user = userList.stream().filter(s->s.getId() == libraryCard.getIdUser()).findFirst().orElse(null);
            int period = 0;
            if(libraryCard.getPeriod().getId() ==1){
                period =6;
            } else if (libraryCard.getPeriod().getId() ==2) {
                period =12;
            } else if (libraryCard.getPeriod().getId() ==3) {
                period = 24;
            }
            String isStatusCard = "";
            if (libraryCard.getCreateDate().plusMonths(period).isBefore(LocalDate.now())){
                isStatusCard = "Hết hạn";
            }else isStatusCard = "Chưa hết hạn";
            System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-20s| %-20s|%-20s| %-23s|\n", libraryCard.getId(), user.getUserName(), user.getPhone(), libraryCard.getTypeCard().getName(), libraryCard.getCreateDate(), libraryCard.getCreateDate().plusMonths(period), isStatusCard,(double) libraryCard.getTypeCard().getId()*50000+"đồng");
        }
    }
    public static List<LibraryCard> readData() {
        File libraryCardFile = new File("./data/libraryCard.txt");
        List<LibraryCard> list = new ArrayList<>();
        String strLine = "";
        try {
            FileReader fileReader = new FileReader(libraryCardFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataLibraryCard = strLine.split(",");
                LibraryCard libraryCard = new LibraryCard(Long.parseLong(strDataLibraryCard[0]), Long.parseLong(strDataLibraryCard[1]), ETypeCard.findByName(strDataLibraryCard[2]), LocalDate.parse(strDataLibraryCard[3]), EPeriod.findByPeriod(strDataLibraryCard[4]));
                list.add(libraryCard);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ignored) {
        }
        return list;
    }
    public boolean checkExist(long id) {
        List<LibraryCard> list = getAllData();
        if (list.isEmpty()) return false;
        LibraryCard libraryCardCheck = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        return libraryCardCheck != null;
    }
}
