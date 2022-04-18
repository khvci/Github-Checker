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
                scanner.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (group.equals("followers")) {
            Path followers = Path.of("src/followers.txt");
            Files.writeString(followers, content.toString());
        } else {
            Path following = Path.of("src/following.txt");
            Files.writeString(following, content.toString());
        }
    }
}
