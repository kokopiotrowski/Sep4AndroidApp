package com.example.sep4androidapp.Entities;

import java.io.Serializable;

public class Fact implements Serializable {
    private String title;
    private String content;

    public Fact(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
