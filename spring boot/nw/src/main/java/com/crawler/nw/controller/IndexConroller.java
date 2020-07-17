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

    @RequestMapping("/index")
    public String index(@RequestParam(value = "likes", required = false) String likes, HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")){
                    int userid = Integer.parseInt(cookie.getValue());
                    //System.out.println(likes + userid);
                    if(likes != null){ //从new跳转来的新用户
                        String[] l = likes.split(",");
                        String L = "";
                        for(int i = 0; i < l.length; i++){
                            Movie m = movieService.getMovieById(Integer.parseInt(l[i]));
                            L += (m.getMovie_type() + "/");
                        }
                        String[] Likes = L.split("/");
                        List list1 = Arrays.asList(Likes);
                        Set set = new HashSet(list1);
                        Likes = (String [])set.toArray(new String[0]);
                        String res = "";
                        for(int i = 0; i < Likes.length; i++){
                            if(i != Likes.length - 1){
                                res = res + Likes[i] + "/";
                            }else{
                                res = res + Likes[i];
                            }
                        }
                        User u =userMapper.getUserById(userid);
                        u.setLike(res);
                        userMapper.updateUserLike(u);
                        model.addAttribute("userid", userid);
                    }else{ //已有喜好的老用户
                        User u =userMapper.getUserById(userid);
                        model.addAttribute("userid", userid);
                    }
                    // 获取主页展示的电影
                    Movie[] Index_movie=new Movie[9];
                    for(int i=0; i<9;i++){
                        Random random = new Random();
                        Index_movie[i] = movieService.getMovieById(random.nextInt(248)+1);
                    }
                    model.addAttribute("index_movies", Index_movie);
                    return "index";
                }
            }
        }
        return "redirect:/";
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
                        System.out.println(random);
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
