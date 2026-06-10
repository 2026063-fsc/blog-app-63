package com.example.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isPresent()) { // isPresent()はOptional専用のデータ有無調べるメソッド
            return optPost.get();
        } else {
            return null;
        }
    }

    public List<Post> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return postRepository.findAll();
        }
        return postRepository.searchByTitle(keyword);
    }
}
