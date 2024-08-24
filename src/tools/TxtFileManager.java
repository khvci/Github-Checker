package tools;

import java.io.File;
import java.io.IOException;

public class TxtFileManager {
    public File create(String txtFileName) {
        File file = new File("src/" + txtFileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void delete(File file) {
        file.delete();
    }
}
