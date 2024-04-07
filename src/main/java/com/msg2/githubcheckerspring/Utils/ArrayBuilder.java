package com.msg2.githubcheckerspring.Utils;

import com.msg2.githubcheckerspring.Entities.MainUser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayBuilder {
    public static List<String> arrayBuilder(
            InputStream inputStream) {
        List<String> groupArray = new ArrayList<>();
        try (Scanner scanner = new Scanner(
                inputStream, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith(
                        "      <a class=\"d-inline-block no-underline mb-1\" data-hovercard-type=\"user\"")) {
                    groupArray.add(UserNameFinder
                            .getUserName(line));
                } else if (line.startsWith(
                        "      <a class=\"d-inline-block no-underline mb-1\" data-hovercard-type=\"organization\"")) {
                    groupArray.add(UserNameFinder
                            .getCompanyName(line));
                }
            }
        }
        return groupArray;
    }
}
