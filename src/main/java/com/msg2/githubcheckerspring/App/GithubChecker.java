package com.msg2.githubcheckerspring.App;

import com.msg2.githubcheckerspring.Entities.MainUser;
import com.msg2.githubcheckerspring.Managers.TxtFileManager;
import com.msg2.githubcheckerspring.Managers.UserManager;
import com.msg2.githubcheckerspring.Utils.ArrayBuilder;
import com.msg2.githubcheckerspring.Utils.PageNumbersGetter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;

public class GithubChecker {
    public static MainUser run(String username) throws IOException, URISyntaxException {
        MainUser user = UserManager.createMainUser(username);
        PageNumbersGetter.readProfilePageSource(user);

        File followersTxtFile = TxtFileManager.createTxtFile(username + "Followers.txt");
        File followingTxtFile = TxtFileManager.createTxtFile(username + "Following.txt");

        InputStream followerStream = Files.newInputStream(followersTxtFile.toPath());
        InputStream followingStream = Files.newInputStream(followingTxtFile.toPath());

        List<String> followersArray = ArrayBuilder.arrayBuilder(followerStream);
        user.setFollowers(followersArray);

        List<String> followingArray = ArrayBuilder.arrayBuilder(followingStream);
        user.setFollowing(followingArray);

        //last step
        TxtFileManager.deleteTxtFile(followersTxtFile);
        TxtFileManager.deleteTxtFile(followingTxtFile);

        return user;
    }

}
