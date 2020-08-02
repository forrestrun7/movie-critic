package com.crawler.nw.controller;

import com.crawler.nw.bean.User;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserInfoController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user/{userid}")
    public String getuserinfo(@PathVariable("userid") int id, Model model, HttpServletRequest request){

        Object object = request.getSession().getAttribute("userid");
            User curruct_user = userMapper.getUserById(id);
            model.addAttribute("userid", id);
            model.addAttribute("username", curruct_user.getUsername());
            model.addAttribute("userlikes", curruct_user.getLike());
            return "user_info";
    }
}
