package com.msg2.githubcheckerspring.Utils;

import com.msg2.githubcheckerspring.Entities.MainUser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;

public class PageNumbersGetter {

    public static void readProfilePageSource(MainUser user)
            throws NumberFormatException, IOException, URISyntaxException {

        StringBuilder profilePageSourceCode = getProfilePageSourceCode(user);

        String followersPageNumberString = splitFollowersString(profilePageSourceCode);
        String followingPageNumberString = splitFollowingString(profilePageSourceCode);

        parseAndSetFollowersPageNumber(user, followersPageNumberString);
        parseAndSetFollowingPageNumbers(user, followingPageNumberString);
    }

    private static StringBuilder getProfilePageSourceCode(MainUser user) throws IOException, URISyntaxException {
        StringBuilder profilePageSourceCode = new StringBuilder();
        URLConnection connection = new URI("https://github.com/"
                + user.getUserName()).toURL().openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        profilePageSourceCode.append(scanner.next());
        return profilePageSourceCode;
    }

    private static String splitFollowersString(StringBuilder profilePageSourceCode) {
        String followersPageString = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("</svg>\n" +
                        "          <span class=\"text-bold color-fg-default\">") + 58,
                profilePageSourceCode.indexOf("</span>\n          followers"));

        return followersPageString;
    }

    private static String splitFollowingString(StringBuilder profilePageSourceCode) {
        String followingPageString = profilePageSourceCode.substring(
                profilePageSourceCode
                        .indexOf("?tab=following\">\n          <span class=\"text-bold color-fg-default\">") + 68,
                profilePageSourceCode
                        .indexOf("</span>\n          following"));
        return followingPageString;
    }

    private static int parseAndSetFollowersPageNumber(MainUser user, String followersPageNumberString) {
        int followersPageNumber = -1;

        try {
            followersPageNumber = (Integer.parseInt(followersPageNumberString) / 50 + 1);
        } catch (Exception ex) {
            System.out.println("exception: followersPageNumber: " + followersPageNumberString);
            String[] temp1 = followersPageNumberString.split("\\.");
            followersPageNumberString = temp1.length < 2 ?
                    temp1[0].split("k")[0] + "000" :
                    temp1[0] + temp1[1].charAt(0) +  "00";

            followersPageNumber = (Integer.parseInt(followersPageNumberString) / 50 + 1);
        } finally {
            user.setFollowersPageNumber(followersPageNumber);
        }
        return followersPageNumber;
    }

    private static int parseAndSetFollowingPageNumbers(MainUser user, String followingPageNumberString) {
        int followingPageNumber = -1;

        try {
            followingPageNumber = (Integer.parseInt(followingPageNumberString) / 50 + 1);
        } catch (Exception ex) {
            System.out.println("exception: followingPageNumber: "+ followingPageNumberString);
            String[] temp2 = followingPageNumberString.split("\\.");
            followingPageNumberString = temp2.length < 2 ?
                    temp2[0].split("k")[0] + "000" :
                    temp2[0] + temp2[1].charAt(0) +  "00";

            followingPageNumber = (Integer.parseInt(followingPageNumberString) / 50 + 1);
        } finally {
            user.setFollowingPageNumber(followingPageNumber);
        }
        return followingPageNumber;
    }
}
