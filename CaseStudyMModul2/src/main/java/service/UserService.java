package service;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Enum.ERole;
import Enum.EGender;

import javax.jws.Oneway;


public class UserService implements IUserService {
    File folder = new File("data");
    boolean a = folder.mkdir();
    File userFile = new File("./data/user.txt");

    @Override
    public void addUser(User user) {
        List<User> userList = this.getAllUser();
        if (userList.isEmpty()) {
            userList.add(user);
        } else if (! (checkExist(user, userList))) {
            userList.add(user);
        }
        this.writeData(userList);
    }

    public boolean checkExist(User user, List<User> list) {
        for (User u : list) {
            if (u.getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteUser(long id) {
        List<User> userList = this.getAllUser();
        if (! userList.isEmpty()) {
            userList.removeIf(u -> u.getId() == id);
            this.writeData(userList);
        }
    }

    @Override
    public void updateUser(long id, User userNew) {
        List<User> userList = this.getAllUser();
        if (! userList.isEmpty()) {
            for (User u : userList) {
                if (u.getId() == id) {
                    u.setId(userNew.getId());
                    u.setName(userNew.getName());
                    u.setPassword(userNew.getPassword());
                    u.setAge(userNew.getAge());
                    u.setEmail(userNew.getEmail());
                    u.setGender(userNew.getGender());
                    u.setRole(userNew.getRole());
                }
            }
        }
        writeData(userList);
    }

    @Override
    public User findUser(long id) {
        List<User> userList = this.getAllUser();
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(userFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                String[] strDataUser = strLine.split(",");
                User user = new User(Long.parseLong(strDataUser[0]), strDataUser[1], strDataUser[2], Integer.parseInt(strDataUser[3]), strDataUser[4], EGender.findByname(strDataUser[5]), ERole.findByRole(strDataUser[6]));
                userList.add(user);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return userList;
    }


    public void writeData(List<User> list) {
        try {
            FileWriter userFileIn = new FileWriter(userFile);
            BufferedWriter bufferedWriter = new BufferedWriter(userFileIn);
            String strInTxt = "";//ko được =null
            for (User u : list) {
                strInTxt += u.toString();
            }
            if (strInTxt != null) {
                bufferedWriter.write(strInTxt);
            }
            bufferedWriter.flush();
            userFileIn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}