package service;

import lombok.Getter;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Enum.ERole;
import Enum.EGender;


@Getter
public class UserService implements IUserService {
    List<User> listUserService = new ArrayList<>();
    File folder = new File("data");
    boolean a = folder.mkdir();
    File userFile = new File("./data/user.txt");


    @Override
    public void addUser(User user) throws IOException {

        List<User> listAddTemp = this.getAllUser();
        if (! checkExist(user, listAddTemp)) {
            listAddTemp.add(user);
            writeUserData(listAddTemp);
        }
    }

    public boolean checkExist(User user, List<User> list) {
        for (User u : list) {
            if (u.getId() == user.getId()) {
                return true;
            } else return false;
        }
        return false;
    }


    @Override
    public List<User> deleteUser(long id) {
        List<User> listUserDeleted;
        listUserDeleted = this.getAllUser().stream().filter(user -> user.getId() != id).collect(Collectors.toList());
        return listUserDeleted;
    }

    @Override
    public void updateUser(long id, User user) {

    }

    @Override
    public User findUser(long id) {
        return this.getAllUser().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try {
//            FileWriter userFileIn = new FileWriter(userFile);
            FileReader fileReader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine = null;
            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strings = strLine.split(",");

                for (String str : strings) {
                    User user = new User();
                    user.setId(Long.parseLong(strings[0]));
                    user.setName(strings[1]);
                    user.setPassword(strings[2]);
                    user.setAge(Integer.parseInt(strings[3]));
                    user.setEmail(strings[4]);
                    user.setGender(EGender.findByname(strings[5]));
                    user.setRole(ERole.findByRole(strings[6]));
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


    @Override
    public void writeUserData(List<User> list) {
        try {
            FileWriter fileWriter = new FileWriter(userFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String strBuf = null;
            for (User u : list) {
                strBuf += u.toString();
            }
            if (strBuf != null) {
                bufferedWriter.write(strBuf);
            }
            bufferedWriter.flush();
            fileWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }


    public static void main(String[] args) {

        User user1 = new User(1001, "Nguyễn Viết Long", "123@@", 27, "vietlongbkdp@gmail.com", EGender.MALE, ERole.ADMIN);
        User user2 = new User(1002, "Hàng Quốc Đạt", "124@@", 26, "vietlongbkdp@gmail.com", EGender.MALE, ERole.USER);
        UserService us1 = new UserService();
        try {
            us1.addUser(user1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            us1.addUser(user2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(us1.getAllUser().toString());
//
//        System.out.println(us1.deleteUser(1002).toString());
    }
}
