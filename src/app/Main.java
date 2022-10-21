package app;

import tools.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main extends Thread {
    private static final long startTime = System.nanoTime();
    static String userToCheck = "";
    private static int followersPageNumber;
    private static int followingPageNumber;

    public static void setFollowersPageNumber(int followersPageNumber) {
        Main.followersPageNumber = followersPageNumber;
    }

    public static void setFollowingPageNumber(int followingPageNumber) {
        Main.followingPageNumber = followingPageNumber;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        UserNumbersGetter.readProfilePageSource(userToCheck);

        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = Files.newInputStream(Path.of("src/followers.txt"));
        InputStream followingStream = Files.newInputStream(Path.of("src/following.txt"));

        Main secondThread = new Main();
        secondThread.start();

        SourceReader sourceReader = new SourceReader(
                userToCheck, followersPageNumber, "followers");
        sourceReader.readSource();

        secondThread.join();

        ArrayList<String> followers = new ArrayList<>();
        ArrayList<String> following = new ArrayList<>();

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        arrayBuilder.arrayBuilder(followers, followerStream);
        arrayBuilder.arrayBuilder(following, followingStream);

        DifferenceFinder differenceFinder = new DifferenceFinder();
        differenceFinder.findDifference(followers, following);

        new ResultPrinter().printResults(
                followers,
                following,
                differenceFinder.dontFollowYou,
                differenceFinder.youDontFollow);

        txtFileManager.deleteTxtFile("followers.txt");
        txtFileManager.deleteTxtFile("following.txt");

        System.out.printf(
                "\nTotal runtime: %d ms.\n",
                (System.nanoTime() - startTime) / 1000000);


    }

    @Override
    public void run() {
        try {
            SourceReader sourceReader2 = new SourceReader(
                    userToCheck, followingPageNumber, "following");
            sourceReader2.readSource();
        } catch (IOException e) {
            System.out.println("second thread problem");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            interrupt();
        }
    }
}
