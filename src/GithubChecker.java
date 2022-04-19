import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GithubChecker {
    public void checkGithub(String userToCheck, int followersPageNumber, int followingPageNumber) throws IOException {
        long startTime = System.nanoTime();

        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> following = new ArrayList<>();

        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");

        SourceReader sourceReader = new SourceReader();
        sourceReader.readSource(userToCheck, followersPageNumber, "followers");
        sourceReader.readSource(userToCheck, followingPageNumber, "following");

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        arrayBuilder.arrayBuilder(followers, followerStream);
        arrayBuilder.arrayBuilder(following, followingStream);

        DifferenceFinder differenceFinder = new DifferenceFinder();
        differenceFinder.findDifference(followers, following);

        ResultPrinter resultPrinter = new ResultPrinter();
        resultPrinter.printResults(startTime, followers, following,
                differenceFinder.dontFollowYou, differenceFinder.youDontFollow);

        txtFileManager.deleteTxtFile("followers.txt");
        txtFileManager.deleteTxtFile("following.txt");

        System.out.println("\nTotal runtime: " + (System.nanoTime() - startTime) / 1000000 + " ms.");
    }
}
