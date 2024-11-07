package tools;

import app.Main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UserNumbersGetter {
    private static final StringBuilder profilePageSourceCode = new StringBuilder();

    public static void readProfilePageSource(String userToCheck) throws NumberFormatException {
        fetchDataFromUserProfile(userToCheck);
        getFollowerPageNumber();
        getFollowingPageNumber();
    }

    private static void fetchDataFromUserProfile(String userToCheck) {
        Scanner scanner;
        URLConnection connection;
        try {
            connection = new URL("https://github.com/" + userToCheck).openConnection();
            scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            profilePageSourceCode.append(scanner.next());
        } catch (Exception e) {
            System.out.println("problem to fetch data from user profile: " + userToCheck);
        }
    }

    private static void getFollowerPageNumber() {
        String followersPageNumberAsString = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("</svg>\n" +
                        "          <span class=\"text-bold color-fg-default\">") + 58,
                profilePageSourceCode.indexOf("</span>\n          followers"));

        try {
            Main.setFollowersPageNumber((Integer.parseInt(followersPageNumberAsString) / 50 + 1));
        } catch (Exception ex) {
            System.out.println("WARNING: followersPageNumberAsString: "+followersPageNumberAsString);
            String[] temp1 = followersPageNumberAsString.split("\\.");
            followersPageNumberAsString = temp1.length < 2 ?
                    temp1[0].split("k")[0] + "000" :
                    temp1[0] + temp1[1].charAt(0) +  "00";

            Main.setFollowersPageNumber(((Integer.parseInt(followersPageNumberAsString) + 100) / 50 + 1));
        }
    }

    private static void getFollowingPageNumber() {
        String followingPageNumberAsString = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf(
                        "?tab=following\">\n          <span class=\"text-bold color-fg-default\">") + 68,
                profilePageSourceCode.indexOf("</span>\n          following"));

        try {
            Main.setFollowingPageNumber((Integer.parseInt(followingPageNumberAsString) / 50 + 1));
        } catch (Exception ex) {
            System.out.println("WARNING: followingPageNumberAsString: "+followingPageNumberAsString);
            String[] temp2 = followingPageNumberAsString.split("\\.");
            followingPageNumberAsString = temp2.length < 2 ?
                    temp2[0].split("k")[0] + "000" :
                    temp2[0] + temp2[1].charAt(0) +  "00";

            Main.setFollowingPageNumber(((Integer.parseInt(followingPageNumberAsString) + 100) / 50 + 1));
        }
    }
}
