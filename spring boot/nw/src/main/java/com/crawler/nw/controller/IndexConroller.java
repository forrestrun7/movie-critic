package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import com.crawler.nw.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class IndexConroller {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MovieService movieService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")){
                    int userid = Integer.parseInt(cookie.getValue());
                    //System.out.println(likes + userid);
                    User u =userMapper.getUserById(userid);
                    model.addAttribute("userid", userid);
                }
            }
        }
        // 获取主页展示的电影
        Movie[] Index_movie=new Movie[9];
        Random random = new Random();
        HashMap hash = new HashMap();
        for(int i=0; i<9; i++){
            int num = random.nextInt(248);
            if(!hash.containsValue(num)){
                hash.put(i,num);
            }
        }
        for(int j=0; j<9; j++){
            Index_movie[j] = movieService.getMovieById((Integer) hash.get(j));
        }
        model.addAttribute("index_movies", Index_movie);
        return "index";
    }
    @GetMapping("/new")
    public String newuser(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")){
                    int userid = Integer.parseInt(cookie.getValue());
                    int moviecount = movieService.getMoviesCount();
                    Movie movies[] = new Movie[9];
                    for(int i = 0; i < 9; i++){
                        int random = (int)(Math.random() * (moviecount - 1)) + 1;
                        //System.out.println(random);
                        movies[i] = movieService.getMovieById(random);
                    }
                    model.addAttribute("userid", userid);
                    model.addAttribute("movies", movies);
                    return "new";
                }
            }
        }
        return "redirect:/";
    }
}
