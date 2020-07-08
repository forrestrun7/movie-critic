package com.crawler.nw.mapper;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import org.apache.ibatis.annotations.*;

public interface MovieMapper {
    @Select("select * from Movie where Moviename=#{Moviename}")
    public Movie getMovieByName(String Moviename);

    @Select("select * from Movie where Movieid=#{Movieid}")
    public Movie getMovieById(int Movieid);

    @Select("select * from Movie")
    public Movie[] getMovies();

    @Delete("delete from Movie where Moviename=#{Moviename}")
    public int deleteMovieByName(String Moviename);

    @Options(useGeneratedKeys = true,keyProperty = "Movieid")
    @Insert("insert into Movie(Moviename, Movietype, Moviescore, Moviedescription, Movieurl) values(#{Moviename}, #{Movietype}, #{Moviescore}, #{Moviedescription}, #{Movieurl})")
    public int insertUser(Movie movie);
}
