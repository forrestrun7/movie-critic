package com.crawler.nw.service;

import com.crawler.nw.bean.Comment;
import com.crawler.nw.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment[] getComments(int movie_id) {
        return commentMapper.getCommentsByUserId(movie_id);
    }

    @Override
    public int setComment(Comment comment) {
        //System.out.println("Imp内：" + "电影id:" + comment.getMovie_id() + ",用户id:" + comment.getUser_id()+ ",用户昵称:" + comment.getUser_nickname()+ ",评论:" + comment.getComment());
        commentMapper.InsertComment(comment);
        return 0;
    }
}
