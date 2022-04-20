import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main extends Thread {
    static long startTime = System.nanoTime();
    static String userToCheck = "khvci";
    static int followersPageNumber;
    static int followingPageNumber;

    public static void main(String[] args) throws IOException, InterruptedException {

        UserNumbersGetter.readProfilePageSource(userToCheck);

        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");

        Main secondThread = new Main();
        secondThread.start();

        SourceReader sourceReader = new SourceReader(userToCheck, followersPageNumber, "followers");
        sourceReader.readSource();

        secondThread.join();

        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> following = new ArrayList<>();

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

    @Override
    public void run() {
        try {
            SourceReader sourceReader2 = new SourceReader(userToCheck, followingPageNumber, "following");
            sourceReader2.readSource();
        } catch (IOException e) {
            System.out.println("second thread problem");
            throw new RuntimeException(e);
        }
    }
}
