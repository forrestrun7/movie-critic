package com.crawler.nw.service;

import com.crawler.nw.bean.Comment;

public interface CommentService {
    Comment[] getComments(int movie_id);
    int setComment(Comment comment);
}
