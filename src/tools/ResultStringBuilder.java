package tools;

import java.util.List;
import java.util.Set;

public class ResultStringBuilder {
    public String CreateResultString(Set<String> followersSet,
                                     Set<String> followingSet,
                                     Set<String> dontFollowYou,
                                     Set<String> youDontFollow) {

        StringBuilder result = new StringBuilder();
        result
                .append("\nFollowers: ").append(followersSet.size())
                .append(" | Following: ").append(followingSet.size())
                .append("\n\nWho does not follow you back: ").append(dontFollowYou.size());

        for (Object s : dontFollowYou) {
            result.append("\n https://github.com/").append(s);
        }

        result
                .append("\n\nWho you do not follow back: ")
                .append(youDontFollow.size()).append("\n\n")
                .append(youDontFollow);

        return result.toString();
    }
}
