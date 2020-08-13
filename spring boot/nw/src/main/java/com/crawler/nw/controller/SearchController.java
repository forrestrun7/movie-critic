package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    MovieService movieService;

    @RequestMapping("/search")
    public String SearchMovies(@RequestParam(value = "Movie_name", required = false) String Movie_name, Model model){
        Movie[] movie_res = movieService.SearchMovieByName(Movie_name);
        model.addAttribute("movie_res", movie_res);
        return "search_page";
    }
}
