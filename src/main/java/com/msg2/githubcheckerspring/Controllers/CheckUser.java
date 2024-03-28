package com.msg2.githubcheckerspring.Controllers;

import com.msg2.githubcheckerspring.Entities.MainUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckUser {
    @GetMapping("/github-checker/{username}")
    public ResponseEntity<String> showUserName(@PathVariable String username) {
        MainUser user = new MainUser();
        user.setUserName(username);
        String userCreated = user.getUserName();
        System.out.println(userCreated + " is created.");
        return new ResponseEntity<>(userCreated, HttpStatus.OK);
    }
}
