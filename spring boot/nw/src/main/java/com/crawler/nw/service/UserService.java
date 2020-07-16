package com.crawler.nw.service;

import com.crawler.nw.bean.User;

public interface UserService {

    User getUserByName(String Username);

    User getUserById(int id);

    int deleteUserById(Integer id);

    int insertUser(User user);

    int updateUserLike(User user);
}
