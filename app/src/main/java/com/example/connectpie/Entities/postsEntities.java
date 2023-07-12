package com.example.connectpie.Entities;

public class postsEntities {

    String username,userid,likes,captions;

    @Override
    public String toString() {
        return "postsEntities{" +
                "username='" + username + '\'' +
                ", userid='" + userid + '\'' +
                ", likes='" + likes + '\'' +
                ", captions='" + captions + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getCaptions() {
        return captions;
    }

    public void setCaptions(String captions) {
        this.captions = captions;
    }

    public postsEntities(String username, String userid, String likes, String captions) {
        this.username = username;
        this.userid = userid;
        this.likes = likes;
        this.captions = captions;
    }
}
