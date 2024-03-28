package com.msg2.githubcheckerspring.Controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUsername {

    @GetMapping("/github-checker/")
    public ResponseEntity<Resource> askUserName() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        Resource resource = new ClassPathResource("templates/form.html");

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}


