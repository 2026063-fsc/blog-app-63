package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BlogController {


    // @GetMapping("/")
    // public String home(Model model) {
    //     List<Post>posts = postService.findAll();
    //     model.addAttribute("posts", posts);
    //     return "home";
    // }

    // @GetMapping("/post/{id}")
    // public String derail(@PathVariable Long id, Model model) {
    //     Post post = postService.findById(id);
    //     model.addAttribute("post", post);
    //     return "detail";
    // }
    
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
