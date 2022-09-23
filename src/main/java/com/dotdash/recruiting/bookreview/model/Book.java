package com.dotdash.recruiting.bookreview.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String author;

    private String title;

    private String imageLink;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
