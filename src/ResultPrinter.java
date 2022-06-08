import java.util.ArrayList;

public class ResultPrinter {
    public void printResults(
            ArrayList<String> followersSet,
            ArrayList<String> followingSet,
            ArrayList<Object> dontFollowYou,
            ArrayList<Object> youDontFollow) {

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
