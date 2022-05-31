import java.util.ArrayList;

public class DifferenceFinder {
    public ArrayList<Object> dontFollowYou = new ArrayList<>();
    public ArrayList<Object> youDontFollow = new ArrayList<>();

    @SuppressWarnings("SuspiciousMethodCalls")
    public void findDifference(
            ArrayList<String> followersArray,
            ArrayList<String> followingArray) {

        for (Object i : followersArray) {
            if (!followingArray.contains(i)) {
                youDontFollow.add(i);
            }
        }

        for (Object j : followingArray) {
            if (!followersArray.contains(j)) {
                dontFollowYou.add(j);
            }
        }
    }
}
