import java.util.ArrayList;

public class ResultPrinter {
    public void printResults(long startTime, ArrayList<String> followersSet, ArrayList<String> followingSet, ArrayList<Object> dontFollowYou,
                                     ArrayList<Object> youDontFollow) {
        System.out.print("\nFollowers: " + followersSet.size());
        System.out.println(" | Following: " + followingSet.size());

        System.out.println("\nWho does not follow you back: " + dontFollowYou.size() + "\n" + dontFollowYou);
        System.out.println("\nWho you do not follow back: " + youDontFollow.size() + "\n" + youDontFollow);
    }
}
