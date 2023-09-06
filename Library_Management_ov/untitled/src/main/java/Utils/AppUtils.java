package Utils;

import java.util.Scanner;

public class AppUtils {
    static Scanner input = new Scanner(System.in);

    public static String typing(String str) {
        System.out.println(str);
        try {
            str = input.nextLine();
        } catch (NullPointerException nullPointerException) {
            System.err.println("Bạn phải nhập vào trước khi bấm Enter");
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Bạn phải nhập đúng định dạng");
        }
        return str;
    }
}



