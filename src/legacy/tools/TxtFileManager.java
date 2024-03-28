package legacy.tools;

import java.io.File;
import java.io.IOException;

public class TxtFileManager {
    public void createTxtFile(String txtFileName) {
        File file = new File("src/" + txtFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTxtFile(String txtFileName) {
        File file = new File("src/" + txtFileName);
        file.delete();
    }
}
