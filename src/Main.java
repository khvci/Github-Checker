import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main extends Thread {
    static String userToCheck = "khvci";
    static int followersPageNumber = 5;
    static int followingPageNumber = 4;

    public static void main(String[] args) throws IOException, InterruptedException {
        //GithubChecker githubChecker = new GithubChecker();
        //githubChecker.checkGithub("khvci", 5, 4);


        long startTime = System.nanoTime();


        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");


        Main secondThread = new Main();
        secondThread.start();

        SourceReader sourceReader = new SourceReader(userToCheck, followersPageNumber, "followers");
        sourceReader.readSource();

        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> following = new ArrayList<>();

        secondThread.join();

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
            SourceReader sourceReader2 = new SourceReader(userToCheck, followersPageNumber, "following");
            sourceReader2.readSource();
        } catch (IOException e) {
            System.out.println("second thread problem");
            throw new RuntimeException(e);
        }
    }
}
