package com.crawler.nw.bean;

public class User {
    int Userid;
    String Username;
    String Userpassword;
    String Userlike;

    public User(){}

    public User(String Username,
                String password){
        this.Username = Username;
        this.Userpassword = password;
    }

    public User(String Username,
            String password,
            String like){
        this.Username = Username;
        this.Userpassword = password;
        this.Userlike = like;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUsername() {
        return Username;
    }

    public void setUserid(int userid) {
        Userid = userid;
    }

    public void setPassword(String password) {
        this.Userpassword = password;
    }

    public void setLike(String like) {
        this.Userlike = like;
    }

    public int getUserid() {
        return Userid;
    }

    public String getPassword() {
        return Userpassword;
    }

    public String getLike() {
        return Userlike;
    }
}
