import java.io.File;
import java.io.IOException;

public class TxtFileManager {
    public void createTxtFile(String txtFileName) {
        File file = new File("src/" + txtFileName);
        try
        {
            file.createNewFile();  //creates a new file
        }
        catch (IOException e)
        {
            e.printStackTrace();    //prints exception if any
        }
    }

    public void deleteTxtFile(String txtFileName) {
        File file = new File("src/" + txtFileName);
        file.delete();
    }
}
