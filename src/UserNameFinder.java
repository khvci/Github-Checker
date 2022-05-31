public class UserNameFinder {
    public static String getUserName(String str) {
        String removeHead = str.substring(
                str.indexOf("      <a class=\"d-inline-block\" " +
                "data-hovercard-type=\"user\" data-hovercard-url=\"/users/") + 86);

        int lastIndex = removeHead.lastIndexOf("/hovercard\" data-octo-click=\"");
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < lastIndex; i++) {
            newString.append(removeHead.charAt(i));
        }

        return newString.toString();
    }
}
