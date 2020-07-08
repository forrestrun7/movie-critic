package com.crawler.nw.controller;

import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;

import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User u = userMapper.getUserByName(username);
        //System.out.println(u.getUsername());
        try {
            if(u.getPassword().equals(password)){
                if(u.getLike() == null){
                    return "redirect:/new";
                }else{
                    model.addAttribute("msg", "登陆成功");
                    return "login";
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
    public String newuser(Model model){
        Movie movie[] = movieMapper.getMovies();
        model.addAttribute("movies", movie);
        return "new";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String reg_insert(@RequestParam("username") String username, @RequestParam("password") String password) {
        //登录成功
        User u = new User(username, password);
        userMapper.insertUser(u);
        return "redirect:/";
    }
}
