package com.example.blog;
//Postクラス

public class Post {
    private Long id;
    private String title;
    private String content;
    private String image_path;

    public Post() { //中身は空でOK（データの読み込み時に引数なしコンストラクタが見つからないエラーが発生する）
    }

    public Post(Long id, String title, String content, String image_path) {
        this.id = id;
        this.title= title;
        this.content= content;
        this.image_path = image_path;
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
        return image_path;
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

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
