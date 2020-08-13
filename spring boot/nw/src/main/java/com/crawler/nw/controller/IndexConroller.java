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

    //主页
    @RequestMapping({"/", "/index"})
    public String index(HttpServletRequest request, Model model){
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null){ //检测是否登录
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("userid")){
//                    int userid = Integer.parseInt(cookie.getValue());
//                    //System.out.println(likes + userid);
//                    model.addAttribute("userid", userid);
//                    //System.out.println("已登录");
//                    break;
//                }
//            }
//        }
        //默认主页
        model.addAttribute("title", "主页 | 未登录");
        model.addAttribute("index_info", "<a href=\"/log\" class=\"btn btn-primary my-2\">登录</a>\n" + "<a href=\"/reg\" class=\"btn btn-secondary my-2\">注册</a>");
        Object object = request.getSession().getAttribute("userid");
        //System.out.println(object);
        if(object != null){
            //model.addAttribute("userid", object);
            //登录后的主页
            model.addAttribute("title", "主页 | " + object);
            model.addAttribute("index_info", "<a href=\"/user/" + object + "\" class=\"btn btn-secondary my-2\">id：" + object + "</a>\n" + "<a href=\"/logout\" class=\"btn btn-secondary my-2\">登出</a>");
            //System.out.println(model.getAttribute("index_info"));
        }
        // 获取主页展示的电影
        int moviecount = movieService.getMoviesCount();
        List<Integer> list = new ArrayList<Integer>();
        Movie Index_movie[] = new Movie[9];
        while(list.size()!=9){
            int random = (int)(Math.random() * (moviecount - 1)) + 1;
            if(!list.contains(random)) {
                list.add(random);
            }
        }
        for(int i = 0; i < 9; i++){
            //System.out.println(random);
            Index_movie[i] = movieService.getMovieById(list.get(i));
        }
        model.addAttribute("index_movies", Index_movie);
        return "index";
    }
    //新用户
    @GetMapping("/new")
    public String newuser(HttpServletRequest request, Model model){
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null){
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("userid")){
//                    int userid = Integer.parseInt(cookie.getValue());
//                    int moviecount = movieService.getMoviesCount();
//                    Movie movies[] = new Movie[9];
//                    for(int i = 0; i < 9; i++){
//                        int random = (int)(Math.random() * (moviecount - 1)) + 1;
//                        //System.out.println(random);
//                        movies[i] = movieService.getMovieById(random);
//                    }
//                    model.addAttribute("userid", userid);
//                    model.addAttribute("movies", movies);
//                    return "new";
//                }
//            }
//        }
        Object object = request.getSession().getAttribute("userid");
            int userid = Integer.parseInt(object.toString());
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
