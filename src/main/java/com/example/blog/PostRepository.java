package com.example.blog;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    private final JdbcClient jdbcClient;

    public PostRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> findAll() {
        return jdbcClient.sql("SELECT id, title, content, image-path FROM posts")
        .query(Post.class)
        .list();
    }

    public Optional<Post> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, content, image-path FROM posts WHERE id = :id")
        .param("id", id)
        .query(Post.class)
        .optional();
    }
}
