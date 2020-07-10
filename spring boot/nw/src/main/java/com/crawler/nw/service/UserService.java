package com.crawler.nw.service;

import com.crawler.nw.bean.User;

public interface UserService {

    public User getUserByName(String Username);

    public User getUserById(int id);

    public int deleteUserById(Integer id);

    public int insertUser(User user);

    public int updateUserLike(User user);
}
