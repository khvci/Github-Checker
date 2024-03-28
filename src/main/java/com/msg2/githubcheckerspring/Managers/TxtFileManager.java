package com.msg2.githubcheckerspring.Managers;

import java.io.File;

public class TxtFileManager {
    public static File createTxtFile(String txtFileName) {
        File file = new File("src/" + txtFileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Problem with file creation");
            //e.printStackTrace();
        }
        return file;
    }

    public static boolean deleteTxtFile(File file) {
        try {
            return file.delete();
        } catch (Exception e) {
            System.out.println("Problem with file deletion.");
            //e.printStackTrace();
            return false;
        }
    }
}
