package com.msg2.githubcheckerspring.Controllers;

import com.msg2.githubcheckerspring.App.GithubChecker;
import com.msg2.githubcheckerspring.Entities.MainUser;
import com.msg2.githubcheckerspring.Managers.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class CheckUser {
    @GetMapping("/github-checker/{username}")
    public ResponseEntity<String> showUserName(@PathVariable String username) throws IOException, URISyntaxException {
        MainUser user = GithubChecker.run(username);

        String controlMessage = UserManager.checkUserStats(user);

        return new ResponseEntity<>(controlMessage, HttpStatus.OK);
    }
}
