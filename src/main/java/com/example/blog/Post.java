package com.example.blog;
//Postクラス

public class Post {
    private Long id;
    private String title;
    private String content;
    private String imagePath;

    public Post(Long id, String title, String content, String imagePath) {
        this.id = id;
        this.title= title;
        this.content= content;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
