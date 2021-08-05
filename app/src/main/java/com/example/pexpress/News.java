package com.example.pexpress;

import java.util.Date;

public class News {
    public String description,title,url;


    public News(String description, String title, String url) {
        this.description = description;
        this.title = title;
        this.url = url;
    }

    public News(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String print(){
        return getTitle() + " " + getDescription() + " " + getUrl();
    }
}
