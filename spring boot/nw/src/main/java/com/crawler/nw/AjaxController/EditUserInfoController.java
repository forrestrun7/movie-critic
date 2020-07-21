package com.crawler.nw.AjaxController;

import com.crawler.nw.bean.User;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class EditUserInfoController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/edit_userinfo")
    public void edit_userinfo(@RequestParam("userid")String userid, @RequestParam("username")String username, @RequestParam("userlikes")String userlikes){
        User new_user = new User();
        new_user.setUserid(Integer.parseInt(userid));
        new_user.setUsername(username);
        new_user.setLike(userlikes);
        userMapper.updateUserinfo(new_user);
    }
}
