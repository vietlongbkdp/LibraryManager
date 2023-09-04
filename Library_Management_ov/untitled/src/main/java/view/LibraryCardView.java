package view;

import Utils.AppUtils;
import Utils.FileUtils;
import Utils.ValidateUntil;
import model.LibraryCard;
import model.User;
import service.LibraryCardService;
import service.UserService;
import Enum.*;

import java.time.LocalDate;
import java.util.List;

public class LibraryCardView {
    public static void libraryCardShowSelection(){
        System.out.println("====================QUẢN LÝ THẺ THƯ VIỆN====================");
        System.out.println("=============================MENU===========================");
        System.out.println("1: Đăng ký thẻ mới");
        System.out.println("2: Hiển thị thông tin thẻ");
        System.out.println("3: Huỷ thẻ");
        System.out.println("0: Quay lại");
    }
    public static void libraryCardSelect(long id){
        List<User> userList = UserService.readData();
        User newUser = userList.stream().filter(s->s.getId() == id).findFirst().orElse(null);
        if(newUser!=null){
        libraryCardShowSelection();
        int select = 0;
        do {
            select = Integer.parseInt(AppUtils.typing("Nhập lựa chọn của bạn: "));
            if(select == 1){
                chekRegisterNewLibraryCard(newUser);
            }else if (select ==2) {
                showLibraryCard(newUser);
            }else if(select == 3) {
                removeLibraryCard(newUser);
            }else if (select == 0){
                LoginView.loginUser(newUser.getId());
            }else if(select!=0) System.out.println("Bạn đã nhập sai rồi, vui lòng nhập lại");
        }while (select!=0);
    }
    }
    public static void chekRegisterNewLibraryCard(User user){
        if(user.isHasCard()){
            System.err.println("Bạn đã có thẻ rồi nhé!!");
        }else {
            registerNewLibraryCard(user.getId());
//            user.setHasCard(true);
//            UserService userService = new UserService();
//            userService.updateById(user.getId(), user);
            List<User> userList = UserService.readData();
            userList.stream().filter(s->s.getId() == user.getId()).findFirst().orElse(null).setHasCard(true);
            FileUtils.writeData(userList, "./data/user.txt");
            System.out.println("Tạo thẻ thư viện thành công, vui lòng đăng nhập lại!!");
            libraryCardSelect(user.getId());
        }
    }
    public static void registerNewLibraryCard(long idUserTrans){
        LibraryCardService libraryCardService = new LibraryCardService();
        long id = 0;
        boolean flag = false;
        do {
            try {
                flag = false;
                id = Long.parseLong(AppUtils.typing("Nhập ID Card: "));
            }catch (NumberFormatException numberFormatException){
                System.err.println("Bạn cần nhập số!");
            }catch (NullPointerException nullPointerException){
                System.err.println("Dữ liệu trống, vui lòng kiểm tra!!");
            }catch (IllegalArgumentException illegalArgumentException){};
            if(!ValidateUntil.checkID(id)) System.err.println("ID phải gồm 4 chữ số");
            if (libraryCardService.checkExist(id)){
                System.err.println("Mời nhập lại!!");
            }else flag = true;
        }while (!ValidateUntil.checkID(id) || !flag);

        String strTypeCard;
        do {
            strTypeCard = AppUtils.typing("Vui lòng chọn loại thẻ :    1: Tiêu chuẩn      2: VIP");
        }while (!ValidateUntil.checkTypeCard(strTypeCard));
        int inputTypeCard = Integer.parseInt(strTypeCard);
        ETypeCard typeCard = ETypeCard.findById(inputTypeCard);

        String strPeriod;
        do {
            strPeriod = AppUtils.typing("Vui lòng chọn thời hạn muốn đăng ký :    1: 6 Tháng      2: 12 Tháng    3: 24 Tháng");
        }while (!ValidateUntil.checkPeriod(strPeriod));
        int inputPeriod = Integer.parseInt(strPeriod);
        EPeriod period = EPeriod.findById(inputPeriod);

        LibraryCard libraryCard = new LibraryCard(id, idUserTrans, typeCard, LocalDate.now(), period);
        libraryCardService.addElement(libraryCard);

    }
    public static void showLibraryCard(User user){
        if(!user.isHasCard()){
            System.out.println("Bạn chưa có thẻ thư viện, vui lòng đăng ký!");
        }else {
        List<LibraryCard> libraryCardList = LibraryCardService.readData();
        LibraryCard libraryCard = libraryCardList.stream().filter(s->s.getIdUser() == user.getId()).findFirst().orElse(null);
            LocalDate dateEnd = null;
            if(libraryCard.getPeriod().getId() == 1){
                dateEnd = libraryCard.getCreateDate().plusMonths(6);
            } else if (libraryCard.getPeriod().getId() == 2) {
                dateEnd = libraryCard.getCreateDate().plusMonths(12);
            }else if (libraryCard.getPeriod().getId() == 3){
                dateEnd = libraryCard.getCreateDate().plusMonths(24);
            }
            System.out.println();
            System.out.println("                       THẺ THƯ VIỆN                      ");
            System.out.println("=========================================================");
            System.out.println(" ╔══════════════════════════════════════════════════════");
            System.out.println(" ║                                                      ");
            System.out.println(" ║   Họ và tên     :    " + user.getUserName());
            System.out.println(" ║   Giới tính     :    " + user.getGender().getName());
            System.out.println(" ║   Ngày sinh     :    " + user.getDoB().toString());
            System.out.println(" ║   SĐT           :    " + user.getPhone());
            System.out.println(" ║   Địa chỉ       :    " + user.getAddress());
            System.out.println(" ║   Email         :    " + user.getEmail());
            System.out.println(" ║   Hạng thẻ      :    " + libraryCard.getTypeCard().getName());
            System.out.println(" ║   Ngày đăng ký  :    " + libraryCard.getCreateDate().toString());
            System.out.println(" ║   Ngày hết hạn  :    " + dateEnd);
            System.out.println(" ║   Mã số thẻ     :    " + libraryCard.getId());
            System.out.println(" ║                                                      ");
            System.out.println(" ╚══════════════════════════════════════════════════════");
        }
    }
    public static void removeLibraryCard(User user) {
        if (!user.isHasCard()) {
            System.out.println("Bạn chưa có thẻ thư viện, không cần phải xoá đâu!");
        } else {
            String isSure = AppUtils.typing("Bạn có chắc chắn muốn xoá thẻ thư viện chứ ? (Y/N)");
            if (isSure.equals("Y")||isSure.equals("y")) {
                List<LibraryCard> cardList = LibraryCardService.readData();
                cardList.remove(cardList.stream().filter(s -> s.getIdUser() == user.getId()).findFirst().orElse(null));
                FileUtils.writeData(cardList, "./data/libraryCard.txt");
                List<User> userList = UserService.readData();
                userList.stream().filter(s -> s.getId() == user.getId()).findFirst().orElse(null).setHasCard(false);
                FileUtils.writeData(userList, "./data/user.txt");

//                System.out.println("Xoá thẻ thành công, vui lòng đăng nhập lại!!");
                libraryCardSelect(user.getId());
            }else System.out.println("Bạn đã đổi ý, không xoá nữa");
        }
    }
}
