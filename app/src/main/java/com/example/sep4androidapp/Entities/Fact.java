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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
