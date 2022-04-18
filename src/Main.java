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
        TxtFileManager txtFileManager = new TxtFileManager();
        txtFileManager.createTxtFile("followers.txt");
        txtFileManager.createTxtFile("following.txt");

        InputStream followerStream = new FileInputStream("src/followers.txt");
        InputStream followingStream = new FileInputStream("src/following.txt");

        getResults("khvci", followerStream, followingStream);

        txtFileManager.deleteTxtFile("followers.txt");
        txtFileManager.deleteTxtFile("following.txt");

    }

    static ArrayList<String> followersSet = new ArrayList<>();
    static ArrayList<String> followingSet = new ArrayList<>();
    static ArrayList<Object> dontFollowYou = new ArrayList<>();
    static ArrayList<Object> youDontFollow = new ArrayList<>();


    private static void getResults(String userName, InputStream followerStream, InputStream followingStream) throws IOException {
        readSource(userName, 5, "followers");
        readSource(userName, 4, "following");

        followersSetBuilder(followersSet, followerStream);

        followingSetBuilder(followingSet, followingStream);

        differenceFinder(followersSet, followingSet, dontFollowYou, youDontFollow);

        printResults(startTime, followersSet, followingSet, dontFollowYou, youDontFollow);

        new FileOutputStream("src/followers.txt").close();
        new FileOutputStream("src/following.txt").close();
    }


    private static void readSource(String userName, int numberOfPages, String group) throws IOException {
        ArrayList<String> linkList = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            linkList.add("https://github.com/" + userName + "?page=" + i + "&tab=" + group);
        }

        StringBuilder content = new StringBuilder();
        for (String s : linkList) {
            URLConnection connection;
            try {
                connection = new URL(s).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content.append(scanner.next());
                scanner.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        if (group.equals("followers")) {
            Path followers = Path.of("src/followers.txt");
            Files.writeString(followers, content.toString());
        } else {
            Path following = Path.of("src/following.txt");
            Files.writeString(following, content.toString());
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


    private static void followingSetBuilder(ArrayList<String> followingSet, InputStream followingStream) {
        try (Scanner scannerFollowing = new Scanner(followingStream, StandardCharsets.UTF_8.name())) {
            while (scannerFollowing.hasNextLine()) {
                String followingLine = scannerFollowing.nextLine();
                if (followingLine.startsWith("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/")) {
                    followingSet.add(getUserName(followingLine));
                }
            }
        }
    }


    private static void followersSetBuilder(ArrayList<String> followersSet, InputStream followerStream) {
        try (Scanner scannerFollower = new Scanner(followerStream, StandardCharsets.UTF_8.name())) {
            while (scannerFollower.hasNextLine()) {
                String followerLine = scannerFollower.nextLine();
                if (followerLine.startsWith("      <a class=\"d-inline-block\" data-hovercard-type=\"user\" data-hovercard-url=\"/users/")) {
                    followersSet.add(getUserName(followerLine));
                }
            }
        }
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
