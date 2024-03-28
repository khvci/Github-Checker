package com.msg2.githubcheckerspring.App;

import com.msg2.githubcheckerspring.Entities.MainUser;
import com.msg2.githubcheckerspring.Managers.TxtFileManager;
import com.msg2.githubcheckerspring.Managers.UserManager;
import com.msg2.githubcheckerspring.Utils.PageNumbersGetter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GithubChecker {
    public static MainUser run(String username) throws IOException, URISyntaxException {
        MainUser user = UserManager.createMainUser(username);
        PageNumbersGetter.readProfilePageSource(user);

        File followersTxtFile = TxtFileManager.createTxtFile(username + "Followers.txt");
        File followingTxtFile = TxtFileManager.createTxtFile(username + "Following.txt");

        //last step
        TxtFileManager.deleteTxtFile(followersTxtFile);
        TxtFileManager.deleteTxtFile(followingTxtFile);

        return user;
    }

}
