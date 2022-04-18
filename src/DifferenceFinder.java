import java.util.ArrayList;

public class DifferenceFinder {
    public ArrayList<Object> dontFollowYou = new ArrayList<>();
    public ArrayList<Object> youDontFollow = new ArrayList<>();

    @SuppressWarnings("SuspiciousMethodCalls")
    public void findDifference(ArrayList<String> followersHash, ArrayList<String> followingHash) {
        for (Object i : followersHash) {
            if (!followingHash.contains(i)) {
                youDontFollow.add(i);
            }
        }

        for (Object j : followingHash) {
            if (!followersHash.contains(j)) {
                dontFollowYou.add(j);
            }
        }
    }
}
