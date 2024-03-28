package com.msg2.githubcheckerspring.Managers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ViewManager {
    public static Resource getUsername() {
        return new ClassPathResource("templates/form.html");
    }
    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        return headers;
    }
}
