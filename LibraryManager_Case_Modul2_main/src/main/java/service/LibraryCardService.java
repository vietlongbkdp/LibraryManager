package service;

import model.LibraryCard;
import model.User;
import Enum.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

import static until.ValidateUntil.checkAccount;

public class LibraryCardService {
    Scanner input = new Scanner(System.in);
    File folder = new File("data");
    boolean a = folder.mkdir();
    File libraryFile = new File("./data/libraryCard.txt");
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
    public void  createCard(long idUser){
        UserService usGet = new UserService();
        List<User> listGet = usGet.getAllUser();
           User userTemp =  listGet.stream().filter(s->s.getId()==idUser).findFirst().get();
        if(userTemp.isHasCard() == false){
                    System.out.println("===========ĐĂNG KÝ THẺ MỚI===========");
                    System.out.println("Nhập ID thẻ: ");
                    long idCard = Long.parseLong(input.nextLine());
                    System.out.println("Nhập mã thẻ: ");
                    String code = input.nextLine();
                    System.out.println("Chọn loại thẻ:         1: Thẻ tiêu chuẩn        2: Thẻ VIP    ");
                    int typeCard = Integer.parseInt(input.nextLine());
                    System.out.println("Chọn thời hạn thẻ:     1: 6 Tháng               2: 12 Tháng            3: 24 Tháng");
                    int period = Integer.parseInt(input.nextLine());
                    LibraryCard libraryCardNew = new LibraryCard(userTemp, idCard, code, ETypeCard.findById(typeCard), LocalDate.now(),EPeriod.findById(period));
                    listGet.add(libraryCardNew);
                    System.out.println(listGet);
            }else System.out.println("Bạn đã có thẻ thư viện rồi nhé!! Vui lòng kiểm tra lại");

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
