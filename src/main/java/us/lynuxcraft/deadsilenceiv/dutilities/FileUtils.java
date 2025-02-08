package us.lynuxcraft.deadsilenceiv.dutilities;

import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static void create(File file){
        try{
            Files.createParentDirs(file);
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String removeExtension(String fileName) {
        if (fileName.indexOf(".") > 0) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }

    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }

    /**
     * Copies a file.
     *
     * @param in the input stream instance
     * @param out the output stream instance.
     */
    public static void copyFile(InputStream in, FileOutputStream out) {
        try {
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
