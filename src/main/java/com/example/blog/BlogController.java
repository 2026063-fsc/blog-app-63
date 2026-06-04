package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BlogController {


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/post/new")
    public String createPost() {
        return "create-post";
    }

    @PostMapping("/post")
    public String submitPost() {
        return "complete";
    }
}
