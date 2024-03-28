package com.msg2.githubcheckerspring.Controllers;

import com.msg2.githubcheckerspring.Entities.MainUser;
import com.msg2.githubcheckerspring.Utils.UserNumbersGetter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CheckUser {
    @GetMapping("/github-checker/{username}")
    public ResponseEntity<String> showUserName(@PathVariable String username) throws IOException {
        MainUser user = new MainUser();
        user.setUserName(username);

        UserNumbersGetter.readProfilePageSource(user);

        String userCreated = user.getUserName();
        System.out.println(userCreated + " is created.");

        String controlMessage = userCreated.concat("<br>"
                + "followers page number: " + user.getFollowersPageNumber() + "<br>"
                + "following page number: " + user.getFollowingPageNumber());

        return new ResponseEntity<>(controlMessage, HttpStatus.OK);
    }
}
