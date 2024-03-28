package com.msg2.githubcheckerspring.Utils;


import com.msg2.githubcheckerspring.Entities.MainUser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class UserNumbersGetter {
    private static final StringBuilder profilePageSourceCode = new StringBuilder();

    public static void readProfilePageSource(MainUser user) throws NumberFormatException, IOException {
        URLConnection connection;
        connection = new URL("https://github.com/" + user.getUserName()).openConnection();

        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        profilePageSourceCode.append(scanner.next());

        String subString1 = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("</svg>\n" +
                        "          <span class=\"text-bold color-fg-default\">") + 58,
                profilePageSourceCode.indexOf("</span>\n          followers"));

        String subString2 = profilePageSourceCode.substring(
                profilePageSourceCode.indexOf("?tab=following\">\n          <span class=\"text-bold color-fg-default\">") + 68,
                profilePageSourceCode.indexOf("</span>\n          following"));

        int followersPageNumber = -1;

        try {
            followersPageNumber = (Integer.parseInt(subString1) / 50 + 1);
        } catch (Exception ex) {
            System.out.println("ERROR: substring1: "+subString1);
            String[] temp1 = subString1.split("\\.");
            subString1 = temp1.length < 2 ?
                    temp1[0].split("k")[0] + "000" :
                    temp1[0] + temp1[1].charAt(0) +  "00";

            followersPageNumber = (Integer.parseInt(subString1) / 50 + 1);
        } finally {
            user.setFollowersPageNumber(followersPageNumber);
        }

        int followingPageNumber = -1;

        try {
            followingPageNumber = (Integer.parseInt(subString2) / 50 + 1);
        } catch (Exception ex) {
            System.out.println("ERROR: substring2: "+subString2);
            String[] temp2 = subString2.split("\\.");
            subString2 = temp2.length < 2 ?
                    temp2[0].split("k")[0] + "000" :
                    temp2[0] + temp2[1].charAt(0) +  "00";

            followingPageNumber = (Integer.parseInt(subString2) / 50 + 1);
        } finally {
            user.setFollowingPageNumber(followingPageNumber);
        }
    }
}
