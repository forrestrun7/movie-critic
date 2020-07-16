package com.crawler.nw.mapper;

import com.crawler.nw.bean.Comment;
import com.crawler.nw.bean.Movie;
import com.crawler.nw.bean.User;
import org.apache.ibatis.annotations.*;

public interface CommentMapper {
    @Select("select * from comment where Movie_id=#{Movie_id}")
    public Comment[] getCommentsByUserId(int Movie_id);

    @Select("select * from comment")
    public Comment[] getComments();

    @Select("select count(*) from comment")
    public int getCommentsCount();

    @Options(useGeneratedKeys = true, keyProperty = "Comment_id")
    @Insert("insert into comment(User_id, User_nickname, Movie_id, Comment) values(#{user_id}, #{user_nickname}, #{movie_id}, #{comment})")
    public int InsertComment(Comment comment);

    @Delete("delete from comment where Comment_id=#{Comment_id}")
    public int deleteCommentById(int Comment_id);
}
