package app;

import tools.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends Thread {
    private static final long startTime = System.nanoTime();
    static String userToCheck = "khvci";
    private static int followersPageNumber;
    private static int followingPageNumber;

    public static void setFollowersPageNumber(int num) {
        followersPageNumber = num;
    }

    public static void setFollowingPageNumber(int num) {
        followingPageNumber = num;
    }

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        UserNumbersGetter.readProfilePageSource(userToCheck);

        TxtFileManager txtFileManager = new TxtFileManager();
        File followersTxtFile = txtFileManager.create("followers.txt");
        File followingTxtFile = txtFileManager.create("following.txt");

        InputStream followerStream = Files.newInputStream(Path.of("src/followers.txt"));
        InputStream followingStream = Files.newInputStream(Path.of("src/following.txt"));

        Main secondThread = new Main();
        secondThread.start();

        SourceReader sourceReader = new SourceReader(
                userToCheck, followersPageNumber, "followers");
        sourceReader.read();

        secondThread.join();

        List<String> followers = new ArrayList<>();
        List<String> following = new ArrayList<>();

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        arrayBuilder.arrayBuilder(followers, followerStream);
        arrayBuilder.arrayBuilder(following, followingStream);

        DifferenceFinder differenceFinder = new DifferenceFinder();
        differenceFinder.findDifference(followers, following);

        ResultStringBuilder resultStringBuilder = new ResultStringBuilder();
        String resultToReturn = resultStringBuilder.CreateResultString(followers,
                following,
                differenceFinder.getDontFollowYou(),
                differenceFinder.getYouDontFollow());
        System.out.println(resultToReturn);

        txtFileManager.delete(followersTxtFile);
        txtFileManager.delete(followingTxtFile);

        System.out.printf(
                "\nTotal runtime: %d ms.\n",
                (System.nanoTime() - startTime) / 1000000);


    }

    @Override
    public void run() {
        try {
            SourceReader sourceReader2 = new SourceReader(
                    userToCheck, followingPageNumber, "following");
            sourceReader2.read();
        } catch (IOException e) {
            System.out.println("second thread problem");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            interrupt();
        } catch (URISyntaxException e) {
            System.out.println("URISyntaxException");
        }
    }
}
