package com.crawler.nw.controller;

import com.crawler.nw.bean.User;

import com.crawler.nw.mapper.MovieMapper;
import com.crawler.nw.mapper.UserMapper;
import com.crawler.nw.service.UserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableAutoConfiguration
@Controller
public class UserController {
    @Autowired
    UserService userservice;

    //登录判定函数
    @RequestMapping("/login")
    public String index(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, HttpSession session, Model model, HttpServletResponse response) {
        User u = userservice.getUserByName(username);
        //System.out.println(u.getUsername());
        try {
            if(u.getPassword().equals(password)){
                // 创建Cookie
//                Cookie login_cookie = new Cookie("userid", (new Integer(u.getUserid())).toString());
//                // 有效期,秒为单位
//                login_cookie.setMaxAge(3600);
//                login_cookie.setPath("/");
//                response.addCookie(login_cookie);
                //session.setAttribute("loginUser",username);
                session.setAttribute("userid", (new Integer(u.getUserid())).toString());
                session.setMaxInactiveInterval(30 * 60);
                if(u.getLike() == null || "".equals(u.getLike())){
                    return "redirect:/new";
                }else{
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

    //注册判定函数
    @RequestMapping("/register")
    public String reg_insert(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, Model model) {
        User u1 = userservice.getUserByName(username);
        if(u1 != null){
            model.addAttribute("msg", "账户已存在,重新输入");
            return "register";
        }else{
            User u2 = new User(username, password);
            userservice.insertUser(u2);
            return "redirect:/new";
        }
    }

    //登出函数
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null){ //获取登录id
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("userid")){
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    response.addCookie(cookie); //删除Cookie
//                    //System.out.println("已删除Cookie：userid");
//                    break;
//                }
//            }
//        }
        request.getSession().invalidate();
        return "redirect:/";
    }
}
