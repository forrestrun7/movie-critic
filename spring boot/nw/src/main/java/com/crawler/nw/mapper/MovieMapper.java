package com.crawler.nw.mapper;

import com.crawler.nw.bean.Movie;
import org.apache.ibatis.annotations.*;

public interface MovieMapper {
    @Select("select * from movie where Movie_name=#{Movie_name}")
    public Movie getMovieByName(String Movie_name);

    @Select("select * from movie where Movie_id=#{Movie_id}")
    public Movie getMovieById(int Movie_id);

    @Select("select * from movie")
    public Movie[] getMovies();

    @Select("select count(*) from movie")
    public int getMoviesCount();

    @Delete("delete from movie where Movie_name=#{Movie_name}")
    public int deleteMovieByName(String Movie_name);

//    @Options(useGeneratedKeys = true,keyProperty = "Movie_id")
//    @Insert("insert into movie(Movie_id, Movietype, Moviescore, Moviedescription, Movieurl) values(#{Moviename}, #{Movietype}, #{Moviescore}, #{Moviedescription}, #{Movieurl})")
//    public int insertMovie(Movie movie);
}
