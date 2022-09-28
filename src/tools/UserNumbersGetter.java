package tools;

import app.Main;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UserNumbersGetter {
    private static final StringBuilder profilePageSourceCode = new StringBuilder();

    public static void readProfilePageSource(String userToCheck) throws NumberFormatException, IOException {
        URLConnection connection;
        connection = new URL("https://github.com/" + userToCheck).openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        profilePageSourceCode.append(scanner.next());

        String subString1 = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("</svg>\n          ") + 58,
                profilePageSourceCode.indexOf("</span>\n          followers"));


        String subString2 = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("?tab=following\">\n          <span class=\"text-bold color-fg-default\">") + 68,
                profilePageSourceCode.indexOf("</span>\n          following"));


        try {
            Main.setFollowersPageNumber((Integer.parseInt(subString1) / 50 + 1));
        } catch (Exception ex) {
            String[] temp1 = subString1.split("\\.");
            subString1 = temp1.length < 2 ?
                    temp1[0].split("k")[0] + "000" :
                    temp1[0] + temp1[1].charAt(0) +  "00";

            app.Main.setFollowersPageNumber(((Integer.parseInt(subString1) + 100) / 50 + 1));
        }

        try {
            Main.setFollowingPageNumber((Integer.parseInt(subString2) / 50 + 1));
        } catch (Exception ex) {

            String[] temp2 = subString2.split("\\.");
            subString2 = temp2.length < 2 ?
                    temp2[0].split("k")[0] + "000" :
                    temp2[0] + temp2[1].charAt(0) +  "00";

            Main.setFollowingPageNumber(((Integer.parseInt(subString2) + 100) / 50 + 1));
        }
    }
}
