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
    
    @GetMapping("/search") //検索画面が押されたとき（GET送信された）
    public String search(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Post> results; //検索結果の記事を入れるための空箱を用意
        if (keyword != null && !keyword.isBlank()) { //入力が空でもスペース・改行等でもない場合
            results = postRepository.search(keyword); //入力keywordを使用し、Repositoryで作成したSQLのLIKE検索を実行
        } else { //入力されていない場合
            results = postRepository.findAll(); //keywordがないためすべての記事を表示
        }
        model.addAttribute("posts", results); //検索結果をいれる
        model.addAttribute("keyword", keyword); //入力した検索文字を残すため
        
        return "search";
    }

    @GetMapping("/post/new") //新規投稿作成が押されたとき（新規投稿をホームに表示）
    public String createPost() {
        return "create-post"; //新規投稿作成画面に飛ぶ
    }

    @PostMapping("/post/create") //投稿完了（投稿完了後、完了画面に飛ぶ）
    public String create(@ModelAttribute Post post, @RequestParam("image") MultipartFile imagFile) {    
        postRepository.save(post);
        return "complete";
    }
    
    @PostMapping("/post/{id}/delete") //投稿を削除する処理（削除後ホームに戻る）
    public String delete(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return "redirect:/";
    }
    
}
