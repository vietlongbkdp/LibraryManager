package service;
import Utils.FileUtils;
import model.LibraryCard;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.*;
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
        if (libraryCardCheck == null) {
            return false;
        } else return true;
    }
}
