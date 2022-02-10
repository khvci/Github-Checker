import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static long startTime = System.nanoTime();

    public static void main(String[] args) throws IOException {

        InputStream followerStream = new FileInputStream(followerPath);
        InputStream followingStream = new FileInputStream(followingPath);

        getResults(followerStream, followingStream);

    }


    static String followerPath = "src/followers.txt";
    static String followingPath = "src/following.txt";

    static ArrayList followersSet = new ArrayList();
    static ArrayList followingSet = new ArrayList();
    static ArrayList dontFollowYou = new ArrayList();
    static ArrayList youDontFollow = new ArrayList();


    private static void getResults(InputStream followerStream, InputStream followingStream) throws IOException {
        readSource("followers");
        readSource("following");

        followersSetBuilder(followersSet, followerStream);

        followingSetBuilder(followingSet, followingStream);

        differenceFinder(followersSet, followingSet, dontFollowYou, youDontFollow);

        printResults(startTime, followersSet, followingSet, dontFollowYou, youDontFollow);

        new FileOutputStream("src/followers.txt").close();
        new FileOutputStream("src/following.txt").close();
    }


    private static void readSource(String group) throws IOException {

        String[] linkList = new String[4];

        if (group.equals("followers")) {
            linkList[0] = "https://github.com/khvci?page=1&tab=followers";
            linkList[1] = "https://github.com/khvci?page=2&tab=followers";
            linkList[2] = "https://github.com/khvci?page=3&tab=followers";
            linkList[3] = "https://github.com/khvci?page=4&tab=followers";
        } else {
            linkList[0] = "https://github.com/khvci?page=1&tab=following";
            linkList[1] = "https://github.com/khvci?page=2&tab=following";
            linkList[2] = "https://github.com/khvci?page=3&tab=following";
            linkList[3] = "https://github.com/khvci?page=4&tab=following";
        }

        String content = null;

        for (String i : linkList) {
            URLConnection connection = null;
            try {
                connection = new URL(i).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content += scanner.next();
                scanner.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (group.equals("followers")) {
            Path followers = Path.of("src/followers.txt");
            Files.writeString(followers, content);
        } else {
            Path following = Path.of("src/following.txt");
            Files.writeString(following, content);
        }
    }



    private static String getUserName(String str) {
        String removeHead = str.substring(str.indexOf("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/") + 86);

        int lastIndex = removeHead.lastIndexOf("/hovercard\" data-octo-click=\"");
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < lastIndex; i++) {
            newString.append(removeHead.charAt(i));
        }

        return newString.toString();
    }


    private static void followingSetBuilder(ArrayList followingSet, InputStream followingStream) {
        try (Scanner scannerFollowing = new Scanner(followingStream, StandardCharsets.UTF_8.name())) {
            while (scannerFollowing.hasNextLine()) {
                String followingLine = scannerFollowing.nextLine();
                if (followingLine.startsWith("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/")) {
                    followingSet.add(getUserName(followingLine));
                }
            }
        }
    }


    private static void followersSetBuilder(ArrayList followersSet, InputStream followerStream) {
        try (Scanner scannerFollower = new Scanner(followerStream, StandardCharsets.UTF_8.name())) {
            while (scannerFollower.hasNextLine()) {
                String followerLine = scannerFollower.nextLine();
                if (followerLine.startsWith("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/")) {
                    followersSet.add(getUserName(followerLine));
                }
            }
        }
    }


    private static void differenceFinder(ArrayList followersHash, ArrayList followingHash, ArrayList dontFollowYou,
                                         ArrayList youDontFollow) {
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


    private static void printResults(long startTime, ArrayList followersSet, ArrayList followingSet, ArrayList dontFollowYou,
                                     ArrayList youDontFollow) {
        System.out.print("\nFollowers: " + followersSet.size());
        System.out.println(" | Following: " + followingSet.size());

        System.out.println("\nWho does not follow you back: " + dontFollowYou.size() + "\n" + dontFollowYou);
        System.out.println("\nWho you do not follow back: " + youDontFollow.size() + "\n" + youDontFollow);

        System.out.println("\nTotal runtime: " + (System.nanoTime() - startTime) / 1000000 + " ms.");
    }

}