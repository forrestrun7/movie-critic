package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexConroller {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MovieMapper movieMapper;

    @RequestMapping("/index")
    public String index(@RequestParam(value = "likes", required = false) String likes, @RequestParam("userid") int userid, Model model){
        System.out.println(likes + userid);
        if(likes != null){
            String[] l = likes.split(",");
            String L = "";
            for(int i = 0; i < l.length; i++){
                Movie m = movieMapper.getMovieById(Integer.parseInt(l[i]));
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
        }else{
            User u =userMapper.getUserById(userid);
            model.addAttribute("userid", userid);
        }
        return "index";

    }
    @GetMapping("/new")
    public String newuser(@RequestParam("userid") int userid, Model model){
        Movie movie[] = movieMapper.getMovies();
        model.addAttribute("userid", userid);
        model.addAttribute("movies", movie);
        model.addAttribute("count", movieMapper.getMoviesCount());
        return "new";
    }
}
