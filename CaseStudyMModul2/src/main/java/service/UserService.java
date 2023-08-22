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
        List<String> listStrUser = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine = null;
            while ((strLine = bufferedReader.readLine()) != null){
                listStrUser.add(strLine);
            }
            bufferedReader.close();
            for (String str: listStrUser) {
                String[] strings = str.split(",");
                    User user = new User();
                    user.setId(Long.parseLong(strings[0]));
                    user.setName(strings[1]);
                    user.setPassword(strings[2]);
                    user.setAge(Integer.parseInt(strings[3]));
                    user.setEmail(strings[4]);
                    user.setGender(EGender.findByname(strings[5]));
                    user.setRole(ERole.findByRole(strings[6]));
//                    User user = new User(Long.parseLong(strings[0]),strings[1],strings[2], Integer.parseInt(strings[3]), strings[4], EGender.valueOf(strings[5]), ERole.valueOf(strings[6]));
                    userList.add(user);

            }
//            listStrUser.stream().forEach(s -> System.out.println(s.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public static void main(String[] args) {
        User user1;
        user1 = new User(1001, "Nguyễn Viết Long", "123@@", 27, "vietlongbkdp@gmail.com", EGender.MALE, ERole.ADMIN);
        User user2 = new User(1002, "Hàng Quốc Đạt", "124@@", 26, "vietlongbkdp@gmail.com", EGender.MALE, ERole.USER);
        UserService us1 = new UserService();
//        us1.addUser(user1);
//        us1.addUser(user2);
        System.out.println(us1.getAllUser().toString());
    }
}
