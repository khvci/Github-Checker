import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class UserNumbersGetter {
    static String userToCheck;
    static ArrayList<Integer> numberOfPages = new ArrayList<>();
    static StringBuilder profilePageSourceCode = new StringBuilder("");

    public static void readProfilePageSource(String userToCheck) throws IOException {
        URLConnection connection;
        try {
            connection = new URL("https://github.com/" + userToCheck).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            profilePageSourceCode.append(scanner.next());
            String subString1 = profilePageSourceCode.substring(profilePageSourceCode.indexOf("</svg>\n" +
                    "          ") + 58,profilePageSourceCode.indexOf("</span>\n" +
                    "          followers"));
            Main.followersPageNumber = (Integer.parseInt(subString1) / 50 + 1);

            String subString2 = profilePageSourceCode.substring(profilePageSourceCode.indexOf(
                    "?tab=following\">\n" +
                    "          <span class=\"text-bold color-fg-default\">") + 68,profilePageSourceCode.indexOf("</span>\n" +
                    "          following"));
            Main.followingPageNumber = (Integer.parseInt(subString2) / 50 + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
