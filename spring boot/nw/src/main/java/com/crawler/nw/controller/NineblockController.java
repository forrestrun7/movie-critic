package com.crawler.nw.controller;

import com.alibaba.fastjson.JSONArray;
import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import com.crawler.nw.service.MovieService;
import com.crawler.nw.service.UserService;
import com.crawler.nw.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@EnableAutoConfiguration
public class NineblockController {

    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;

    @PostMapping("/change")
    public Response change(){
        int moviecount = movieService.getMoviesCount();
        Movie movies[] = new Movie[9];
        for(int i = 0; i < 9; i++){
            int random = (int)(Math.random() * (moviecount - 1)) + 1;
            //System.out.println(random);
            movies[i] = movieService.getMovieById(random);
        }
        Response response = new Response("1", JSONArray.toJSONString(movies));
        return response;
    }

    @PostMapping("/updatelikes")
    public void updatelikes(String likes, int userid){
//        System.out.println(likes);
//        System.out.println(userid);
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
        User u =userService.getUserById(userid);
        u.setLike(res);
        userService.updateUserLike(u);
    }
}
