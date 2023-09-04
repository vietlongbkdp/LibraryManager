package Utils;

import java.util.Scanner;

public class AppUtils {
    static Scanner input = new Scanner(System.in);
    public static String typing(String str){
        System.out.println(str);
        return input.nextLine();
    }

}
