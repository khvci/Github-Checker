package com.msg2.githubcheckerspring.Utils;

import java.util.ArrayList;
import java.util.List;

public class DifferenceFinder {
    public static List<String> findDiff(List<String> arrayToCheck, List<String> usersToCheck) {
        List<String> diff = new ArrayList<>();
        for (String str:usersToCheck) {
            if (!arrayToCheck.contains(str)) {
                diff.add(str);
            }
        }
        return diff;
    }
}
