package com.msg2.githubcheckerspring.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SourceReader {
//    String userName;
//    int numberOfPages;
//    String group;
//
//    public SourceReader(String userName, int numberOfPages, String group) {
//        this.userName = userName;
//        this.numberOfPages = numberOfPages;
//        this.group = group;
//    }

    public static ArrayList<String> read(String userName, int numberOfPages, String group) throws IOException, InterruptedException, URISyntaxException {
        ArrayList<String> linkList = new ArrayList<>();
        for (int i = 1; i <= numberOfPages; i++) {
            linkList.add(String.format(
                    "https://github.com/%s?page=%d&tab=%s",
                    userName, i, group));
        }

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < linkList.size(); i++) {

            URLConnection connection;
            try {
                connection = new URI(linkList.get(i)).toURL().openConnection();
                scanConnection(content, connection);
            } catch (Exception ex) {
                System.out.printf("%d pages has been read, sleeping for 1 min to prevent from HTTP 429 (too many requests).%n", i);
                Thread.sleep(60000);

                connection = new URI(linkList.get(i)).toURL().openConnection();
                scanConnection(content, connection);
            }
        }

        Path path = Path.of(String.format("src/%s%s.txt", userName, group));
        Files.writeString(path, content);
        System.out.println("links: " + linkList);
        return linkList;
    }

    private static void scanConnection(StringBuilder content, URLConnection connection) throws IOException {
        Scanner scanner = new Scanner(connection.getInputStream());
        scanner.useDelimiter("\\Z");
        content.append(scanner.next());
        scanner.close();
    }
}
