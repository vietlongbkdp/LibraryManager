package service;

import model.LibraryCard;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryCardService {
    Scanner input = new Scanner(System.in);
    File folder = new File("data");
    boolean a = folder.mkdir();
    File libraryFile = new File("./data/libraryCard.txt");
    public void createCard(long idUser){
        System.out.println("");
    }
    public void writeData(List<LibraryCard> list) {
        try {
            FileWriter libraryFileIn = new FileWriter(libraryFile);
            BufferedWriter bufferedWriter = new BufferedWriter(libraryFileIn);
            String strInTxt = "";
            for (LibraryCard l : list) {
                strInTxt += l.toString();
            }
            if (strInTxt != "") {
                bufferedWriter.write(strInTxt);
            }
            bufferedWriter.flush();
            libraryFileIn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LibraryCard> getAllCard(){
        List<LibraryCard> libraryCardsList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(libraryFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataLibrary = strLine.split(",");

//                User user = new User(Long.parseLong(strDataUser[0]), strDataUser[1],
//                        strDataUser[2], strDataUser[3], strDataUser[4], strDataUser[5], LocalDate.parse(strDataUser[6]), strDataUser[7],
//                        EGender.findByName(strDataUser[8]), ERole.findByName(strDataUser[9]));
//                userList.add(user);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return libraryCardsList;
    }
}
