import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SourceReader {
    public void readSource(String userName, int numberOfPages, String group) throws IOException {
        ArrayList<String> linkList = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            linkList.add("https://github.com/" + userName + "?page=" + i + "&tab=" + group);
        }

        StringBuilder content = new StringBuilder();
        for (String s : linkList) {
            URLConnection connection;
            try {
                connection = new URL(s).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content.append(scanner.next());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Path path = Path.of("src/" + group + ".txt");
        Files.writeString(path, content.toString());
    }
}
