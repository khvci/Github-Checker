import java.util.ArrayList;

public class ResultPrinter {
    public void printResults(
            ArrayList<String> followersSet,
            ArrayList<String> followingSet,
            ArrayList<Object> dontFollowYou,
            ArrayList<Object> youDontFollow) {

        System.out.print("\nFollowers: " + followersSet.size());
        System.out.println(" | Following: " + followingSet.size());


        System.out.println(String.format(
                "\nWho does not follow you back: %d\n%s",
                dontFollowYou.size(),
                dontFollowYou));

        System.out.println(String.format(
                "\nWho you do not follow back: %d\n%s",
                youDontFollow.size(),
                youDontFollow));


    }
}
