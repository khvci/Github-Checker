package com.msg2.githubcheckerspring.Controllers;

import com.msg2.githubcheckerspring.Managers.UsernameManager;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsername {

    @GetMapping("/github-checker/")
    public ResponseEntity<Resource> askUserName() {
        return ResponseEntity.ok()
                .headers(UsernameManager.getHttpHeaders())
                .body(UsernameManager.getUsername());
    }
}


