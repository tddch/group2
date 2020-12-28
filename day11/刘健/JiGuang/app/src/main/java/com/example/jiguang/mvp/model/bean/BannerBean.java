package com.example.jiguang.mvp.model.bean;

import java.util.ArrayList;

/**
 *
 */
public class BannerBean extends ArrayList<BannerBean> {
    private int image;
    private String title;

    public BannerBean() {
    }

    public BannerBean(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
