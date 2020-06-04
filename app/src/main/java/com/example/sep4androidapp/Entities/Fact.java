package com.example.sep4androidapp.Entities;

import java.io.Serializable;

public class Fact implements Serializable {
    private String title;
    private String content;
    private String source;
    private String sourceUrl;

    public Fact(String title, String content, String source, String sourceUrl) {
        this.title = title;
        this.content = content;
        this.source = source;
        this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }
}
