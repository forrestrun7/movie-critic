package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MovieController {
    @Autowired
    MovieMapper movieMapper;

    @GetMapping("/movie/{movie_id}")
    public String movieInfo(@PathVariable("movie_id")int movie_id, Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")){
                    int userid = Integer.parseInt(cookie.getValue());
                    Movie movie = movieMapper.getMovieById(movie_id);
                    model.addAttribute("movie_id", movie_id);
                    model.addAttribute("userid", userid);
                    model.addAttribute("movie", movie);
                    return "movie_info";
                }
            }
        }
        return "redirect:/";
    }
}
