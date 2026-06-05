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
        return jdbcClient.sql("SELECT id, title, content, image_path FROM posts")
                .query((rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_path")))
                .list();
    }

    public Optional<Post> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, content,image_path FROM posts WHERE id = :id")
                .param("id", id)
                .query((rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_path")))
                .optional();
    }

   public void save(Post post) {
        jdbcClient.sql("INSERT INTO posts (title, content, image_path) VALUES (:title, :content, :imagePath)")
        .param("title", post.getTitle())
        .param("content", post.getContent())
        .param("imagePath", post.getImagePath()) 
        .update(); ///データの更新
    }
}
