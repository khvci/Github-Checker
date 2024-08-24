package tools;

import java.util.ArrayList;
import java.util.List;

public class DifferenceFinder {
    private final List<String> dontFollowYou = new ArrayList<>();
    private final List<String> youDontFollow = new ArrayList<>();

    public void findDifference(
            List<String> followersArray,
            List<String> followingArray) {

        // Find users who follow you but you don't follow back
        for (String follower : followersArray) {
            if (!followingArray.contains(follower)) {
                youDontFollow.add(follower);
            }
        }

        // Find users who you follow but they don't follow you back
        for (String followingTo : followingArray) {
            if (!followersArray.contains(followingTo)) {
                dontFollowYou.add(followingTo);
            }
        }
    }

    //getters
    public List<String> getDontFollowYou() {
        return dontFollowYou;
    }

    public List<String> getYouDontFollow() {
        return youDontFollow;
    }
}
