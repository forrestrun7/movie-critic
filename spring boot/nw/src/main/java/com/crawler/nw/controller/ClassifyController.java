package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassifyController {

    @Autowired
    MovieService movieService;

    //分类展示
    @RequestMapping("/class")
    public String classify_movie(HttpServletRequest request, Model model) {
        int count = movieService.getMoviesCount();
        List fiction_movie = new ArrayList();
        List crime_movie = new ArrayList();
        List comedy_movie = new ArrayList();
        List fantasy_movie = new ArrayList();
        List action_movie = new ArrayList();
        List cartoon_movie = new ArrayList();
        List drama_movie = new ArrayList();
        Movie movie[] = movieService.getMovies();
        //循环分类movie表中的电影
        for (int i = 0; i <= count-2; i++) {
            if (movie[i].getMovie_type().contains("犯罪")) {
                crime_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("喜剧")) {
                comedy_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("科幻")) {
                fiction_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("奇幻")) {
                fantasy_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("动作")) {
                action_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("动画")) {
                cartoon_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("剧情")) {
                drama_movie.add(movie[i]);
            }
        }
        model.addAttribute("fiction_movie", fiction_movie);
        model.addAttribute("comedy_movie", comedy_movie);
        model.addAttribute("crime_movie", crime_movie);
        model.addAttribute("fantasy_movie", fantasy_movie);
        model.addAttribute("action_movie", action_movie);
        model.addAttribute("cartoon_movie", cartoon_movie);
        model.addAttribute("drama_movie", drama_movie);
        return "class";
    }
    //分类详情,偷懒暂时这样弄，要改进的
    @RequestMapping("/class/{movie_type}")
    public String Movie_type_classify(@PathVariable("movie_type")String movie_type, Model model){
        int count = movieService.getMoviesCount();
        List fiction_movie = new ArrayList();
        List crime_movie = new ArrayList();
        List comedy_movie = new ArrayList();
        List fantasy_movie = new ArrayList();
        List action_movie = new ArrayList();
        List cartoon_movie = new ArrayList();
        List drama_movie = new ArrayList();
        Movie movie[] = movieService.getMovies();
        //循环分类movie表中的电影
        for (int i = 0; i <= count-2; i++) {
            if (movie[i].getMovie_type().contains("犯罪")) {
                crime_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("喜剧")) {
                comedy_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("科幻")) {
                fiction_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("奇幻")) {
                fantasy_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("动作")) {
                action_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("动画")) {
                cartoon_movie.add(movie[i]);
            }
            if (movie[i].getMovie_type().contains("剧情")) {
                drama_movie.add(movie[i]);
            }
        }
        model.addAttribute("fiction_movie", fiction_movie);
        model.addAttribute("comedy_movie", comedy_movie);
        model.addAttribute("crime_movie", crime_movie);
        model.addAttribute("fantasy_movie", fantasy_movie);
        model.addAttribute("action_movie", action_movie);
        model.addAttribute("cartoon_movie", cartoon_movie);
        model.addAttribute("drama_movie", drama_movie);
        model.addAttribute("movie_type", movie_type);
        return "movie_class";
    }
}