package com.example.dns.project_3.entity;

public class News {
    private String pathImg;
    private String title;
    private String description;
    private String date;
    private Profile profile;
    private long like;

    public News(String pathImg, String title, String description, String date,Profile profile, long like) {
        this.pathImg = pathImg;
        this.title = title;
        this.description = description;
        this.date = date;
        this.like = like;
        this.profile = profile;
    }

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }

}
