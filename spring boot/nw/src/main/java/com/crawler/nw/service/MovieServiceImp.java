package com.crawler.nw.service;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    public MovieMapper movieMapper;

    @Override
    public Movie getMovieByName(String Movie_name) {
        Movie movie = movieMapper.getMovieByName(Movie_name);
        return movie;
    }

    @Override
    public Movie getMovieById(int Movie_id) {
        Movie movie = movieMapper.getMovieById(Movie_id);
        return movie;
    }

    @Override
    public Movie[] getMovies() {
        Movie[] movie = movieMapper.getMovies();
        return movie;
    }

    @Override
    public int getMoviesCount() {
        int count = movieMapper.getMoviesCount();
        return count;
    }
    @Override
    public int deleteMovieByName(String Movie_name) {
        movieMapper.deleteMovieByName(Movie_name);
        return 0;
    }

    @Override
    public Movie[] SearchMovieByName(String Movie_name){
        Movie[] movie = movieMapper.SearchMovieByName(Movie_name);
        return movie;
    }
}
