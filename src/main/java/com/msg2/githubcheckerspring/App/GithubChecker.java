package com.msg2.githubcheckerspring.App;

import com.msg2.githubcheckerspring.Entities.MainUser;
import com.msg2.githubcheckerspring.Managers.TxtFileManager;
import com.msg2.githubcheckerspring.Managers.UserManager;
import com.msg2.githubcheckerspring.Utils.ArrayBuilder;
import com.msg2.githubcheckerspring.Utils.DifferenceFinder;
import com.msg2.githubcheckerspring.Utils.PageNumbersGetter;
import com.msg2.githubcheckerspring.Utils.SourceReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class GithubChecker {
    public static MainUser run(String username) throws IOException, URISyntaxException, InterruptedException {
        MainUser user = UserManager.createMainUser(username);
        PageNumbersGetter.readProfilePageSource(user);

        File followersTxtFile = TxtFileManager.createTxtFile(username + "followers.txt");
        File followingTxtFile = TxtFileManager.createTxtFile(username + "following.txt");

        InputStream followerStream = Files.newInputStream(followersTxtFile.toPath());
        InputStream followingStream = Files.newInputStream(followingTxtFile.toPath());

        SourceReader.read(user.getUserName(), user.getFollowersPageNumber(), "followers");
        SourceReader.read(user.getUserName(), user.getFollowingPageNumber(), "following");

        List<String> followersArray = ArrayBuilder.arrayBuilder(followerStream);
        user.setFollowers(followersArray);
        List<String> followingArray = ArrayBuilder.arrayBuilder(followingStream);
        user.setFollowing(followingArray);

        List<String> youDontFollow = DifferenceFinder.findDiff(user.getFollowers(), user.getFollowing());
        user.setYouDontFollow(youDontFollow);

        List<String> dontFollowYou = DifferenceFinder.findDiff(user.getFollowing(), user.getFollowers());
        user.setDontFollowYou(dontFollowYou);

//        System.out.println("dont follow you back: " + user.getDontFollowYou());
//        System.out.println("you dont follow back: " + user.getYouDontFollow());
//
//        System.out.println("followers: " + followersArray.size());
//        System.out.println("following: " + followingArray.size());

        //last step
        TxtFileManager.deleteTxtFile(followersTxtFile);
        TxtFileManager.deleteTxtFile(followingTxtFile);

        return user;
    }

}
