import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SourceReader {
    String userName;
    int numberOfPages;
    String group;

    public SourceReader(String userName, int numberOfPages, String group) {
        this.userName = userName;
        this.numberOfPages = numberOfPages;
        this.group = group;
    }

    public void readSource() throws IOException {
        ArrayList<String> linkList = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {

            linkList.add(String.format(
                    "https://github.com/%s?page=%d&tab=%s",
                    userName, i, group));
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

        Path path = Path.of(String.format("src/%s.txt", group));
        Files.writeString(path, content);
    }
}
