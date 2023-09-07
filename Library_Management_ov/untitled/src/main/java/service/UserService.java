package service;
import Utils.AppUtils;
import Utils.FileUtils;
import model.Book;
import model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.*;
import view.BookView;
import view.UserView;

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
        UserService userService = new UserService();
        List<User> userList = userService.getAllData();
        for (User b : userList) {
            if (b.getId() == id) {
                b.setId(user.getId());
                b.setAccount(user.getAccount());
                b.setPassword(user.getPassword());
                b.setUserName(user.getUserName());
                b.setPhone(user.getPhone());
                b.setAddress(user.getAddress());
                b.setDoB(user.getDoB());
                b.setEmail(user.getEmail());
                b.setGender(user.getGender());
                b.setRole(user.getRole());
                b.setHasCard(user.isHasCard());
            }
            FileUtils.writeData(userList, linkDBUser);
        }
    }
    public void deleteById(long id) {
        List<User> userList = readData();
        userList.stream().filter(s -> s.getId() == id).findFirst().ifPresent(userList::remove);
        FileUtils.writeData(userList, linkDBUser);
    }

    public void showData() {
        List<User> userList = readData();
        if (userList.isEmpty()) {
            System.out.println("Danh sách User đang trống, vui lòng thêm vào!!");
        } else {
            showUserDetail(userList);
        }
    }

    public void showUserDetail(List<User> list) {
        System.out.println("                                                              Danh sách User hiện có ");
        System.out.println("=================================================================================================================================================================================");
        System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-15s| %-12s| %-23s| %-15s| %-20s| %-15s|\n", "ID", "Tên đăng nhập", "Họ và tên", "SĐT", "Địa chỉ", "Ngày sinh", "Email", "Giới tính", "Phân quyền", "Thẻ thư viện");
        for (User u : list) {
            String cardStatus = "";
            if (u.isHasCard()) {
                cardStatus = "Đã có thẻ";
            } else cardStatus = "Chưa có";
            System.out.printf("|%-4s| %-20s| %-20s| %-20s| %-15s| %-12s| %-23s| %-15s| %-20s| %-15s|\n", u.getId(), u.getAccount(), u.getUserName(), u.getPhone(), u.getAddress(), u.getDoB(), u.getEmail(), u.getGender().getName(), u.getRole().getName(), cardStatus);
        }
    }

    public boolean checkExist(long id) {
        List<User> list = getAllData();
        if (list.isEmpty()) return false;
        User userCheck = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (userCheck == null) {
            return false;
        } else return true;
    }
    public void setAdmin(long id) {
        List<User> list = getAllData();
        User user = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (user == null) {
            System.out.println("ID không tồn tại, vui lòng kiểm tra lại!");
        }else {
            if(user.getRole().getName().equals("Quản trị viên")){
                System.out.println("Người này đã là quản trị viên rồi, vui lòng kiểm tra lại");
            }else {
                String isSure = AppUtils.typing("Bạn có chắc chắn muốn chỉ định người này làm quản trị viên không? (Y/N)");
                if(isSure.equals("Y")||isSure.equals("y")){
                    user.setRole(ERole.ADMIN);
                    FileUtils.writeData(list, linkDBUser);
                    System.out.println("Bạn đã chỉ định Quản trị viên thành công");
                }else System.out.println("Bạn đã đổi ý");
            }
        }
    }
    public void removeAdmin(long id) {
        List<User> list = getAllData();
        User user = list.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (user == null) {
            System.out.println("ID không tồn tại, vui lòng kiểm tra lại!");
        } else {
            if (user.getRole().getName().equals("Đọc giả")) {
                System.out.println("Người này không phải là Quản trị viên, vui lòng kiểm tra lại!");
            } else {
                String isSure = AppUtils.typing("Bạn có chắc chắn muốn xoá quyền Quản trị viên không? (Y/N)");
                if (isSure.equals("Y") || isSure.equals("y")) {
                    user.setRole(ERole.CLIENT);
                    FileUtils.writeData(list, linkDBUser);
                    System.out.println("Bạn đã xoá quyền Quản trị viên thành công");
                } else System.out.println("Bạn đã đổi ý");
            }
        }
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
        } catch (IOException ignored) {
        }
        return list;
    }

    public void findShow() {
        System.out.println("TÌM USER: ");
        System.out.println("1: Tìm theo tên");
        System.out.println("2: Tìm theo giới tính");
        System.out.println("3: Tìm theo Admin/Client");
        System.out.println("4: Đã có thẻ thư viện");
        System.out.println("0: Quay lại");
    }

    public void findUser(User user) {
        findShow();
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Bạn muốn tìm theo: "));
            if (select == 1) {
                String strToFind = AppUtils.typing("Nhập tên user bạn muốn tìm");
                List<User> userList = getAllData();
                List<User> userListToName = new ArrayList<>();
                for (User u : userList) {
                    if (u.getUserName().contains(strToFind)) {
                        userListToName.add(u);
                    }
                }
                showUserDetail(userListToName);
                findUser(user);
            } else if (select == 2) {
                int strToFind = Integer.parseInt(AppUtils.typing("Chọn giới tính :  1: Nam     2: Nữ     3: Bêđê"));
                List<User> userList = getAllData();
                List<User> userListToGender = new ArrayList<>();
                for (User u : userList) {
                    if (u.getGender().getId() == strToFind) {
                        userListToGender.add(u);
                    }
                }
                showUserDetail(userListToGender);
                findUser(user);
            } else if (select == 3) {
                int strToFind = Integer.parseInt(AppUtils.typing("Nhập thể loại bạn muốn tìm: 1: Đọc giả    2: Quản trị viên"));
                List<User> userList = getAllData();
                List<User> userListToRole = new ArrayList<>();
                for (User u : userList) {
                    if (u.getRole().getId() == strToFind) {
                        userListToRole.add(u);
                    }
                }
                showUserDetail(userListToRole);
                findUser(user);
            } else if (select == 4) {
                Boolean strToFind = false;
                String strInput = AppUtils.typing("Nhập thể loại bạn muốn tìm: 1: Đã có    2: Chưa có");
                if (strInput.equals("1")) strToFind = true;
                List<User> userList = getAllData();
                List<User> userListHasCard = new ArrayList<>();
                for (User u : userList) {
                    if (u.isHasCard()) {
                        userListHasCard.add(u);
                    }
                }
                showUserDetail(userListHasCard);
                findUser(user);
            } else if (select == 0) {
                UserView.userSelect(user);
            } else System.out.println("Bạn đã nhập không đúng, vui lòng nhập lại!!!");
        } while (select != 0);
    }
    /**
     let a = {
        age : 12
     }

     console.log(a ||
     */
}