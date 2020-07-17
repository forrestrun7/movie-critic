package com.crawler.nw.service;

import com.crawler.nw.bean.User;
import com.crawler.nw.service.UserService;
import com.crawler.nw.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService{
    @Autowired
    public UserMapper userMapper;

    @Override
    public User getUserByName(String Username) {
        User user = userMapper.getUserByName(Username);
        return user;
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public int deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
        return 0;
    }

    @Override
    public int insertUser(User user) {
        userMapper.insertUser(user);
        return 0;
    }

    @Override
    public int updateUserLike(User user) {
        userMapper.updateUserLike(user);
        return 0;
    }
}
