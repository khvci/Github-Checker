import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        GithubChecker githubChecker = new GithubChecker();
        githubChecker.checkGithub("khvci", 5, 4);
    }


}
