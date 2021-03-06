package com.crawler.nw.AjaxController;

import com.alibaba.fastjson.JSONArray;
import com.crawler.nw.bean.Comment;
import com.crawler.nw.service.CommentService;
import com.crawler.nw.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class CommentController {

    @Autowired
    CommentService commentService;

    //上传评论
    @RequestMapping(value = "/updatecomment")
    public void setComment(@RequestParam(value = "user_id") int user_id, @RequestParam(value = "user_nickname", required = false) String user_nickname, @RequestParam(value = "movie_id") int movie_id, @RequestParam(value = "comment") String comm){
        Comment comment = new Comment();
        comment.setMovie_id(movie_id);
        comment.setUser_id(user_id);
        if(user_nickname == null){
            comment.setUser_nickname("user" + user_id);
        }else{
            comment.setUser_nickname(user_nickname);
        }
        comment.setComment(comm);
        //System.out.println("Controller内：" + "电影id:" + comment.getMovie_id() + ",用户id:" + comment.getUser_id()+ ",用户昵称:" + comment.getUser_nickname()+ ",评论:" + comment.getComment());
        commentService.setComment(comment);
    }

    //拉取评论
    @RequestMapping(value = "/getcomment")
    public Response getComment(@RequestParam(value = "movie_id") int movie_id){
        List<Comment> comments = Arrays.asList( commentService.getComments(movie_id));
        if(comments.size()>0){
            //System.out.println(JSONArray.toJSONString(comments));
            return new Response("0", JSONArray.toJSONString(comments));
        }else{
            return new Response("1", "没有留言");
        }
    }
}
