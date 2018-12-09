package com.app.roman.mob_dev.model;

import java.util.List;

public class DiskPhoto {

    private String text;
    private List<String> urls;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
