package com.crawler.nw.service;
import com.crawler.nw.bean.Movie;

public interface MovieService {
    public Movie getMovieByName(String Movie_name);

    public Movie getMovieById(int Movie_id);

    public Movie[] getMovies();

    public int getMoviesCount();

    public int deleteMovieByName(String Movie_name);

}
