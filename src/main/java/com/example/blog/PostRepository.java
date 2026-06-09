package com.example.blog;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository { //JdbcClientでデータベースを扱う
    private final JdbcClient jdbcClient;

    public PostRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> findAll() { //findAll()メソッド
        String sql = "SELECT id, title, content, image_path FROM posts";
        return jdbcClient.sql(sql)
                .query(Post.class)
                .list();
    }

    public Optional<Post> findById(Long id) { //findById()メソッド
        String sql = "SELECT id, title, content,image_path FROM posts WHERE id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Post.class)
                .optional();
    }

    public void save(Post post) { //saveメソッド (新規投稿をホーム)
        String sql = "INSERT INTO posts (title, content, image_path) VALUES (:title, :content, :imagePath)";
        jdbcClient.sql(sql)
                .param("title", post.getTitle())
                .param("content", post.getContent())
                .param("imagePath", post.getImagePath())
                .update(); //データの更新
    }

    public List<Post> searchByTitle(String keyword) { //検索search()
        String sql = "SELECT id, title, content, image_path FROM posts WHERE title LIKE :keyword OR content LIKE :keyword";
        String safeKeyword = "%" + keyword + "%";
        return jdbcClient.sql(sql)
                .param("keyword", safeKeyword)
                .query(Post.class)
                .list();
    }

    public void deleteById(Long id) {
        String sql ="DELETE FROM posts WHERE id = :id";
        jdbcClient.sql(sql)
        .param("id", id)
        .update(); //データの削除もデータの更新
    }
}
