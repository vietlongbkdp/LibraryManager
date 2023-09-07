package Utils;

import java.io.*;
import java.util.List;
import java.lang.*;

public class FileUtils {
    static File folder = new File("data");
    static boolean a = folder.mkdir();

    public static <T> void writeData(List<T> list, String linkDB){
        File userFile = new File(linkDB);
        String strWrite = "";
        try {
            FileWriter fileWriter = new FileWriter(userFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (T e:list) {
                strWrite += e.toString();
            }
            if(strWrite !=""){
                bufferedWriter.write(strWrite);
            }
            bufferedWriter.flush();
            fileWriter.close();
        } catch (IOException ignored) {
        }
    }

}
