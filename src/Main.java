import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    static long startTime = System.nanoTime();
    static String userName = "khvci";
    static ArrayList<String> followersSet = new ArrayList<>();
    static ArrayList<String> followingSet = new ArrayList<>();
    static ArrayList<Object> dontFollowYou = new ArrayList<>();
    static ArrayList<Object> youDontFollow = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");

        getResults(userName, followerStream, followingStream);

        txtFileManager.deleteTxtFile("followers.txt");
        txtFileManager.deleteTxtFile("following.txt");

    }


    private static void getResults(String userName, InputStream followerStream, InputStream followingStream) throws IOException {
        SourceReader sourceReader = new SourceReader();
        sourceReader.readSource(userName, 5, "followers");
        sourceReader.readSource(userName, 4, "following");

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        arrayBuilder.arrayBuilder(followersSet, followerStream);
        arrayBuilder.arrayBuilder(followingSet, followingStream);

        differenceFinder(followersSet, followingSet, dontFollowYou, youDontFollow);

        printResults(startTime, followersSet, followingSet, dontFollowYou, youDontFollow);
    }


    public static String getUserName(String str) {
        String removeHead = str.substring(str.indexOf("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/") + 86);

        int lastIndex = removeHead.lastIndexOf("/hovercard\" data-octo-click=\"");
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < lastIndex; i++) {
            newString.append(removeHead.charAt(i));
        }

        return newString.toString();
    }


    @SuppressWarnings("SuspiciousMethodCalls")
    private static void differenceFinder(ArrayList<String> followersHash, ArrayList<String> followingHash, ArrayList<Object> dontFollowYou,
                                         ArrayList<Object> youDontFollow) {
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


    private static void printResults(long startTime, ArrayList<String> followersSet, ArrayList<String> followingSet, ArrayList<Object> dontFollowYou,
                                     ArrayList<Object> youDontFollow) {
        System.out.print("\nFollowers: " + followersSet.size());
        System.out.println(" | Following: " + followingSet.size());

        System.out.println("\nWho does not follow you back: " + dontFollowYou.size() + "\n" + dontFollowYou);
        System.out.println("\nWho you do not follow back: " + youDontFollow.size() + "\n" + youDontFollow);

        System.out.println("\nTotal runtime: " + (System.nanoTime() - startTime) / 1000000 + " ms.");
    }
}
