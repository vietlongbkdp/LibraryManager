package service;
import Utils.FileUtils;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.*;

public class UserService {
    static File folder = new File("data");
    static boolean a = folder.mkdir();
    public static final String linkDBUser = "./data/user.txt";

    public User getById(long id) {
        List<User> userList = readData();
        return userList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public List<User> getAllData() {
        return readData();
    }

    public void addElement(User user) {
        List<User> userList = readData();
        userList.add(user);
        FileUtils.writeData(userList, linkDBUser);
    }

    public void updateById(long id, User user) {
        List<User> userList = readData();
        User userOld = userList.stream().filter(userItem -> userItem.getId() == id).findFirst().orElse(null);
        if (userOld != null) {
            userOld.setId(user.getId());
            userOld.setAccount(user.getAccount());
            userOld.setPassword(user.getPassword());
            userOld.setUserName(user.getUserName());
            userOld.setPhone(user.getPhone());
            userOld.setAddress(user.getAddress());
            userOld.setDoB(user.getDoB());
            userOld.setEmail(user.getEmail());
            userOld.setGender(user.getGender());
            userOld.setRole(user.getRole());
            userOld.setHasCard(user.isHasCard());
        }
        FileUtils.writeData(userList, linkDBUser);
    }

    public void deleteById(long id) {
        List<User> userList = readData();
        userList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(userList::remove);
        FileUtils.writeData(userList, linkDBUser);
    }

    public void showData() {
        List<User> userList = readData();
        StringBuilder strShowData = new StringBuilder();
        for (User u : userList) {
            strShowData.append(u.toString());
        }
        System.out.println(strShowData);
    }

    public boolean checkExist(long id) {
        List<User> list = getAllData();
        if (list.isEmpty()) return false;
        User userCheck = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (userCheck == null) {
            return false;
        } else return true;
    }

    public static List<User> readData() {
        File userFile = new File("./data/user.txt");
        List<User> list = new ArrayList<>();
        String strLine = "";
        try {
            FileReader fileReader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataUser = strLine.split(",");
                User user = new User(Long.parseLong(strDataUser[0]), strDataUser[1],
                        strDataUser[2], strDataUser[3], strDataUser[4], strDataUser[5], LocalDate.parse(strDataUser[6]), strDataUser[7],
                        EGender.findByName(strDataUser[8]), ERole.findByName(strDataUser[9]), Boolean.parseBoolean(strDataUser[10]));
                list.add(user);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("LỖI TRONG QUÁ TRÌNH ĐỌC DỮ LIỆU");
        }
        return list;
    }
}