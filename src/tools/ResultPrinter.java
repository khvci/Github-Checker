package tools;

import java.util.List;

public class ResultPrinter {
    public void printResults(
            List<String> followersSet,
            List<String> followingSet,
            List<Object> dontFollowYou,
            List<Object> youDontFollow) {

        System.out.printf(
                "\nFollowers: %d | Following: %d\n",
                followersSet.size(),
                followingSet.size());

        System.out.printf(
                "\nWho does not follow you back: %d\n%s%n",
                dontFollowYou.size(),
                dontFollowYou);

        System.out.printf(
                "\nWho you do not follow back: %d\n%s%n",
                youDontFollow.size(),
                youDontFollow);
    }
}
