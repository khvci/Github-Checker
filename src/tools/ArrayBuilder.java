package tools;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ArrayBuilder {
    public void arrayBuilder(
            Set<String> groupArray,
            InputStream inputStream) {

        try (Scanner scannerFollowing = new Scanner(
                inputStream, StandardCharsets.UTF_8)) {
            while (scannerFollowing.hasNextLine()) {
                String followingLine = scannerFollowing.nextLine();
                if (followingLine.startsWith(
                        "      <a class=\"d-inline-block no-underline mb-1\" data-hovercard-type=\"user\"")) {
                    groupArray.add(UserNameFinder
                            .getUserName(followingLine));
                } else if (followingLine.startsWith(
                        "      <a class=\"d-inline-block no-underline mb-1\" data-hovercard-type=\"organization\"")) {
                    groupArray.add(UserNameFinder
                            .getCompanyName(followingLine));
                }
            }
        }
    }
}
