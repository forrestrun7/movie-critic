package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;

import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class mainController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MovieMapper movieMapper;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/reg")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(@RequestParam("username") String username, @RequestParam("password") String password, Model model, RedirectAttributes attributes) {
        User u = userMapper.getUserByName(username);
        //System.out.println(u.getUsername());
        try {
            if(u.getPassword().equals(password)){
                if(u.getLike() == null){
                    attributes.addAttribute("userid", u.getUserid());
                    return "redirect:/new";
                }else{
                    attributes.addAttribute("userid", u.getUserid());
                    return "redirect:/index";
                }
            }else{
                model.addAttribute("msg", "密码错误，登陆失败");
                return "login";
            }
        }catch(NullPointerException e){
            model.addAttribute("msg", "用户名不存在，登录失败");
            return "login";
        }
    }

    @GetMapping("/new")
    public String newuser(@RequestParam("userid") int userid, Model model){
        Movie movie[] = movieMapper.getMovies();
        model.addAttribute("userid", userid);
        model.addAttribute("movies", movie);
        model.addAttribute("count", movieMapper.getMoviesCount());
        return "new";
    }

    @RequestMapping("/index")
    public String index(@RequestParam(value = "likes", required = false) String likes, @RequestParam("userid") int userid, Model model){
        System.out.println(likes + userid);
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
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String reg_insert(@RequestParam("username") String username, @RequestParam("password") String password) {
        //登录成功
        User u = new User(username, password);
        userMapper.insertUser(u);
        return "redirect:/";
    }
}
