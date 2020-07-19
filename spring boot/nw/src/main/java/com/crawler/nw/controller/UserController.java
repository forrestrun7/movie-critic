package com.crawler.nw.controller;

import com.crawler.nw.bean.User;

import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import com.crawler.nw.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    UserService userservice;

    @GetMapping("/log")
    public String index() {
        return "login";
    }

    @GetMapping("/reg")
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model, HttpServletResponse response) {
        User u = userservice.getUserByName(username);
        //System.out.println(u.getUsername());
        try {
            if(u.getPassword().equals(password)){
                // 创建Cookie
                Cookie login_cookie = new Cookie("userid", (new Integer(u.getUserid())).toString());
                // 有效期,秒为单位
                login_cookie.setMaxAge(3600);
                response.addCookie(login_cookie);
                session.setAttribute("loginUser",username);
                if(u.getLike() == null || "".equals(u.getLike())){
                    return "redirect:/new";
                }else{
//                    Cookie like_cookie = new Cookie("likes", u.getLike());
//                    like_cookie.setMaxAge(3600);
//                    response.addCookie(like_cookie);
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String reg_insert(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        User u1 = userservice.getUserByName(username);
        if(u1 != null){
            model.addAttribute("msg", "账户已存在,重新输入");
            return "register";
        }
        else{
            User u2 = new User(username, password);
            userservice.insertUser(u2);
            return "redirect:/";
        }
    }

}
