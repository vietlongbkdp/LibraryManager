package service;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Enum.ERole;
import Enum.EGender;


public class UserService implements IUserService{
    File folder = new File("data");
    boolean a = folder.mkdir();
    File userFile = new File("./data/user.txt");


    @Override
    public void addUser(User user){
        try {
            FileWriter userFileIn = new FileWriter(userFile, true);
            userFileIn.write(user.toString());
            userFileIn.close();

        } catch (FileNotFoundException ignored){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public void updateUser(long id, User user) {

    }

    @Override
    public User findUser(long id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        List<User> list1 = new ArrayList<>();
        try {
            FileReader userFileOut = new FileReader(userFile);
            BufferedReader bufferedReader =new BufferedReader(userFileOut);
            while (bufferedReader.readLine() != null){
                String[] strData = bufferedReader.readLine().split(",");
                User user = new User();

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void main(String[] args) {
        User user1 = new User(1001, "Nguyễn Viết Long", "123@@", 27, "vietlongbkdp@gmail.com", EGender.MALE, ERole.ADMIN);
        User user2 = new User(1001, "Nguyễn Viết Đạt", "123@@", 27, "vietlongbkdp@gmail.com", EGender.MALE, ERole.ADMIN);
        UserService us1 = new UserService();
        us1.addUser(user1);
        us1.addUser(user2);
        us1.deleteUser(1001);
    }
}
