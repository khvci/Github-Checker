package tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DifferenceFinder {
    private final Set<String> dontFollowYou = new HashSet<>();
    private final Set<String> youDontFollow = new HashSet<>();

    public void findDifference(
            Set<String> followersArray,
            Set<String> followingArray) {

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
    public Set<String> getDontFollowYou() {
        return dontFollowYou;
    }

    public Set<String> getYouDontFollow() {
        return youDontFollow;
    }
}
