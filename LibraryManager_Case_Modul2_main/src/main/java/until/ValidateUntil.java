package until;

import java.util.regex.Pattern;

public class ValidateUntil {
    public static final String REGEX_ID = ("^[0-9]{4}$");
    public static final String REGEX_Account = ("^[A-Za-z0-9]+$");
    public static final String REGEX_Name = ("^[A-Za-z ]+$");
    public static final String REGEX_Phone = ("^[0][1-9][0-9]{8}$");
    public static final String REGEX_Address = ("^[A-Za-z0-9, ]{3,}$");
    public static final String REGEX_DoB = ("^[1-2][0-9]{3}[-][0-1][0-9][-][0-3][0-9]$");
    public static final String REGEX_Email = ("^[a-zA-Z0-9_]+@[a-z]+[.][a-z]+$");
    public static final String REGEX_Gender = ("^[1-3]$");
    public static final String REGEX_Password = ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    public static boolean checkID(long id){
        String idConvert = String.valueOf(id);
        return Pattern.matches(REGEX_ID, idConvert);
    }
    public static boolean checkAccount(String account){
        return Pattern.matches(REGEX_Account, account);
    }
    public static boolean checkName(String Name){
        return Pattern.matches(REGEX_Name, Name);
    }
    public static boolean checkPhone(String phone){
        return Pattern.matches(REGEX_Phone, phone);
    }
    public static boolean checkAddress(String Address){
        return Pattern.matches(REGEX_Address, Address);
    }
    public static boolean checkDoB(String DoB){
        return Pattern.matches(REGEX_DoB, DoB);
    }
    public static boolean checkGender(String gender){
        return Pattern.matches(REGEX_Gender,gender);
    }
    public static boolean checkEmail(String email){
        return Pattern.matches(REGEX_Email, email);
    }
    public static boolean checkPassword(String password){
        return Pattern.matches(REGEX_Password, password);
    }

}
