package com.crawler.nw.mapper;

import com.crawler.nw.bean.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("select * from User where Username=#{Username}")
    public User getUserByName(String Username);

    @Select("select * from User where Userid=#{Userid}")
    public User getUserById(int id);

    @Delete("delete from User where Userid=#{Userid}")
    public int deleteUserById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "Userid")
    @Insert("insert into User(Username, Userpassword, Userlike) values(#{Username}, #{Userpassword}, #{Userlike})")
    public int insertUser(User user);

    @Update("update User set Userlike=#{Userlike} where Userid=#{Userid}")
    public int updateUserLike(User user);
}
