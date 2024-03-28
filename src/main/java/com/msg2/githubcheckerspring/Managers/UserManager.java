package com.msg2.githubcheckerspring.Managers;

import com.msg2.githubcheckerspring.Entities.MainUser;

public class UserManager {
    public static MainUser createMainUser(String username) {
        MainUser user = new MainUser();
        user.setUserName(username);
        return user;
    }

    public static void setPageNumbers(MainUser user) {

    }

    public static String checkUserStats(MainUser user) {
        String userCreated = user.getUserName();
        System.out.println(userCreated + " is created.");

        String controlMessage = userCreated.concat("<br>"
                + "followers page number: " + user.getFollowersPageNumber() + "<br>"
                + "following page number: " + user.getFollowingPageNumber());

        return controlMessage;
    }
}
