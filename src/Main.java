import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    static long startTime = System.nanoTime();
    static String userName = "khvci";
    static ArrayList<String> followersSet = new ArrayList<>();
    static ArrayList<String> followingSet = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");

        SourceReader sourceReader = new SourceReader();
        sourceReader.readSource(userName, 5, "followers");
        sourceReader.readSource(userName, 4, "following");

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        arrayBuilder.arrayBuilder(followersSet, followerStream);
        arrayBuilder.arrayBuilder(followingSet, followingStream);

        DifferenceFinder differenceFinder = new DifferenceFinder();
        differenceFinder.findDifference(followersSet, followingSet);

        ResultPrinter resultPrinter = new ResultPrinter();
        resultPrinter.printResults(startTime, followersSet, followingSet,
                differenceFinder.dontFollowYou, differenceFinder.youDontFollow);

        txtFileManager.deleteTxtFile("followers.txt");
        txtFileManager.deleteTxtFile("following.txt");

        System.out.println("\nTotal runtime: " + (System.nanoTime() - startTime) / 1000000 + " ms.");
    }
}
