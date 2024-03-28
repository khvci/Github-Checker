package legacy.tools;

import java.util.ArrayList;
import java.util.List;

public class DifferenceFinder {
    public List<Object> dontFollowYou = new ArrayList<>();
    public List<Object> youDontFollow = new ArrayList<>();

    @SuppressWarnings("SuspiciousMethodCalls")
    public void findDifference(
            List<String> followersArray,
            List<String> followingArray) {

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
