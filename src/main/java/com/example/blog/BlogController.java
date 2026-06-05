package com.example.blog;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class BlogController {

    private final PostRepository postRepository;
    private final PostService postService;

    public BlogController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

   @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/post/{id}")
    public String derail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "detail";
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

    @PostMapping("/post/create")
    public String create(@ModelAttribute Post post, @RequestParam("image") MultipartFile imagFile) {    
        postRepository.save(post);
        return "redirect:/";
    }
    
}
