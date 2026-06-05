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
        return jdbcClient.sql("SELECT id, title, content, image_path FROM posts")
                .query((rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_path")))
                .list();
    }

    public Optional<Post> findById(Long id) { //findById()メソッド
        return jdbcClient.sql("SELECT id, title, content,image_path FROM posts WHERE id = :id")
                .param("id", id)
                .query((rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_path")))
                .optional();
    }

    public void save(Post post) { //saveメソッド
        jdbcClient.sql("INSERT INTO posts (title, content, image_path) VALUES (:title, :content, :imagePath)")
                .param("title", post.getTitle())
                .param("content", post.getContent())
                .param("imagePath", post.getImagePath())
                .update(); ///データの更新
    }

    public List<Post> search(String keyword) { //検索search()
        String sql = "SELECT id, title, content, image_path FROM posts WHERE title LIKE :keyword OR content LIKE :keyword";

        String safeKeyword = "%" + keyword + "%";
        return jdbcClient.sql(sql)
                .param("keyword", safeKeyword)
                .query((rs, rowNum) -> new Post(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image_path")))
                .list();
    }

    public void deleteById(Long id) {
        String sql ="DELETE FROM posts WHERE id = :id";

        jdbcClient.sql(sql)
        .param("id", id)
        .update(); //データの削除もデータの更新
    }
}
