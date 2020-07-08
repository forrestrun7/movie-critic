package com.crawler.nw.bean;

public class Movie {
    int Movieid;
    String Moviename;
    int Movietype;
    int Moviescore;
    String Moviedescription;
    String Movieurl;

    public void setMovieid(int movieid) {
        Movieid = movieid;
    }

    public void setName(String name) {
        Moviename = name;
    }

    public void setType(int type) {
        Movietype = type;
    }

    public void setScore(int score) {
        Moviescore = score;
    }

    public void setDescription(String description) {
        Moviedescription = description;
    }

    public void setUrl(String url) {
        Movieurl = url;
    }

    public int getMovieid() {
        return Movieid;
    }

    public String getName() {
        return Moviename;
    }

    public int getType() {
        return Movietype;
    }

    public int getScore() {
        return Moviescore;
    }

    public String getDescription() {
        return Moviedescription;
    }

    public String getUrl() {
        return Movieurl;
    }
}
