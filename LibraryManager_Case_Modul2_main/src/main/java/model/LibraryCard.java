package model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import Enum.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard extends User{
    private long idCard;
    private String code;
    private ETypeCard typeCard;
    private LocalDate createDate;
    private LocalDate expiredDate;
    private EPrice price;

//    public LibraryCard(long id, String account, String password, String userName, String phone, String address, LocalDate doB, String email, EGender gender, ERole role, long idCard, String code, ETypeCard typeCard, LocalDate createDate, LocalDate expiredDate, EPrice price) {
//        super(id, account, password, userName, phone, address, doB, email, gender, role);
//        this.idCard = idCard;
//        this.code = code;
//        this.typeCard = typeCard;
//        this.createDate = createDate;
//        this.expiredDate = expiredDate;
//        this.price = price;
//    }
    public LibraryCard(User user, long idCard, String code, ETypeCard typeCard, LocalDate createDate, LocalDate expiredDate, EPrice price){

    }

    public void showLibraryCard(){
        System.out.println("=====================THẺ THƯ VIỆN=====================");
        System.out.printf("Mã số thẻ : " + this.getCode());
        System.out.printf("Họ và tên : " + this.getUserName());
        System.out.printf("Ngày sinh : " + this.getDoB());
        System.out.printf("Giới tính : " + this.getGender());
        System.out.printf("Số điện thoại : " + this.getPhone());
        System.out.printf("Địa chỉ : " + this.getAddress());
        System.out.printf("Ngày sinh : " + this.getDoB());
        System.out.printf("Hạng thẻ : " + this.getTypeCard());
        System.out.printf("Ngày đăng ký : " + this.getCreateDate());
        System.out.printf("Ngày hết hạn : " + this.getExpiredDate());
    }

}