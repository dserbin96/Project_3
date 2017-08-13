package com.example.dns.project_3.entity;

public class Profile {

    private String pathImg;
    private String userName;
    private int userId;
    private String description;
    private String sex;
    private String statys;

    public Profile(int userId,String pathImg,String userName,String sex,String description,String statys)
    {
        this.description = description;
        this.pathImg = pathImg;
        this.sex = sex;
        this.statys = statys;
        this.userId = userId;
        this.userName = userName;
    }

    public String getPathImg() {
        return pathImg;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getSex() {
        return sex;
    }

    public String getStatys() {
        return statys;
    }
}
