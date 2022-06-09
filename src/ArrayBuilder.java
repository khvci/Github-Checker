import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayBuilder {
    public void arrayBuilder(
            ArrayList<String> groupArray,
            InputStream inputStream) {

        try (Scanner scannerFollowing = new Scanner(
                inputStream, StandardCharsets.UTF_8.name())) {
            while (scannerFollowing.hasNextLine()) {
                String followingLine = scannerFollowing.nextLine();
                if (followingLine.startsWith(
                        "      <a class=\"d-inline-block ")) {
                    groupArray.add(UserNameFinder
                            .getUserName(followingLine));
                }
            }
        }
    }
}
