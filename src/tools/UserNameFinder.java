package tools;

public class UserNameFinder {
    public static String getUserName(String str) {
        return str
                .split("user\" data-hovercard-url=\"/users/")[1]
                .split("/hovercard\" data-octo-click=")[0];
    }

    public static String getCompanyName(String str) {
        return str
                .split("data-hovercard-url=\"/orgs/")[1]
                .split("/hovercard\" data-octo-click")[0];
    }
}
