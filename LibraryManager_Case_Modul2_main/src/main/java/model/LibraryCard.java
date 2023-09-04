package model;

import java.time.LocalDate;
import java.util.Scanner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Enum.*;
import service.LibraryCardService;


@Getter
@Setter
@NoArgsConstructor

public class LibraryCard extends User{
    Scanner input = new Scanner(System.in);
    private long idCard;
    private String code;
    private ETypeCard typeCard;
    private LocalDate createDate;
    private EPeriod period;
    LibraryCardService libraryCardService = new LibraryCardService();

    public LibraryCard(long idCard, String code, ETypeCard typeCard, LocalDate createDate, EPeriod period) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.doB = user.getDoB();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.role = user.getRole();
        this.hasCard = user.isHasCard();
        this.idCard = idCard;
        this.code = code;
        this.typeCard = typeCard;
        this.createDate = createDate;
        this.period = period;
    }

    //    public LibraryCard(long id, String account, String password, String userName, String phone, String address, LocalDate doB, String email, EGender gender, ERole role, long idCard, String code, ETypeCard typeCard, LocalDate createDate, LocalDate expiredDate, EPrice price) {
//        super(id, account, password, userName, phone, address, doB, email, gender, role);
//        this.idCard = idCard;
//        this.code = code;
//        this.typeCard = typeCard;
//        this.createDate = createDate;
//        this.expiredDate = expiredDate;
//        this.price = price;
//    }
//    public LibraryCard(long idCard, String code, ETypeCard typeCard, LocalDate createDate, EPeriod period){
//        this.idCard = idCard;
//        this.code = code;
//        this.typeCard = typeCard;
//        this.createDate = createDate;
//        this.period = period;
//    }

    public String toString() {
        return idCard + "," + code + "," + typeCard + "," + createDate + "," + period +"\n" ;
    }

    public void showLibraryCard(){
        System.out.println("             =====================THẺ THƯ VIỆN=====================");
        System.out.println("             | |  Mã số thẻ : " + this.getCode());
        System.out.println("             | |  Họ và tên : " + this.getUserName());
        System.out.println("             | |  Ngày sinh : " + this.getDoB());
        System.out.println("             | |  Giới tính : " + this.getGender());
        System.out.println("             | |  Số điện thoại : " + this.getPhone());
        System.out.println("             | |  Địa chỉ : " + this.getAddress());
        System.out.println("             | |  Ngày sinh : " + this.getDoB());
        System.out.println("             | |  Hạng thẻ : " + this.getTypeCard());
        System.out.println("             | |  Ngày đăng ký : " + this.getCreateDate());
        System.out.println("             ======================================================");
    }
    public void selectLibraryCard(){
        int index = 0;
        do {
            System.out.println("============THẺ THƯ VIỆN==========");
            System.out.println("1: ĐĂNG KÝ THẺ MỚI");
            System.out.println("2: KIỂM TRA THÔNG TIN THẺ");
            System.out.println("3: HUỶ THẺ");
            System.out.println("0: THOÁT RA");
            System.out.println("Lựa chọn của bạn: ");
            index = Integer.parseInt(input.nextLine());
            if(index == 1){

                libraryCardService.createCard(this.id);
            } else if (index == 2) {
                System.out.println("KIỂM TRA THÔNG TIN THẺ");
            }else if (index ==3){
                System.out.println("HUỶ THẺ");
            } else if (index!=0) {
                System.out.println("LỰA CHỌN CỦA BẠN KHÔNG ĐÚNG, VUI LÒNG CHỌN LẠI!");
            }
        }while (index!=0);


    }

}