package com.example.sep4androidapp.connection.responses;

import com.example.sep4androidapp.Entities.Fact;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FactResponse implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;

    public Fact getFact(){
        return new Fact(title,content);
    }
}
