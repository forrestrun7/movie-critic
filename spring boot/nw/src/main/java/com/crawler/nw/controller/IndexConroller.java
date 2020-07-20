package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import com.crawler.nw.service.MovieService;
import com.crawler.nw.service.UserService;
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
    UserService userService;
    @Autowired
    MovieService movieService;

    //跳转登录页
    @GetMapping("/log")
    public String index() {
        return "login";
    }

    //跳转注册页
    @GetMapping("/reg")
    public String register(){
        return "register";
    }

    //主页
    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model){
        int flag = 0; //0未登录， 1已登录
        Cookie[] cookies = request.getCookies();
        if (cookies != null){ //检测是否登录
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")){
                    int userid = Integer.parseInt(cookie.getValue());
                    //System.out.println(likes + userid);
                    model.addAttribute("userid", userid);
                    flag = 1;
                    //System.out.println("已登录");
                    break;
                }
            }
        }
        // 获取主页展示的电影
        int moviecount = movieService.getMoviesCount();
        Movie Index_movie[] = new Movie[9];
        for(int i = 0; i < 9; i++){
            int random = (int)(Math.random() * (moviecount - 1)) + 1;
            //System.out.println(random);
            Index_movie[i] = movieService.getMovieById(random);
        }
        model.addAttribute("index_movies", Index_movie);
        if (flag == 1){
            return "index_logged";
        }else {
            return "index";
        }
    }
    //新用户
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
