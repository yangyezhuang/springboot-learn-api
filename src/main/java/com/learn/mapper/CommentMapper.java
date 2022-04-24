package com.learn.mapper;

import com.learn.model.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CommentMapper {

    // 查询课程评论
    @Select("select * from user_comments_tbl;")
    List<Comment> allComments();


    // 查询课程评论
    @Select("select * from user_comments_tbl where course_id=#{course_id};")
    List<Comment> getComment(int course_id);


    // 添加评论
    @Insert("insert into user_comments_tbl(uid,username,course_id,comment,date,comment_id) " +
            "values(#{uid},#{username},#{course_id},#{comment},#{date},#{comment_id});")
    int addComment(int uid, String username, int course_id, String date, String comment, double comment_id);


    // 删除评论
    @Delete("delete from user_comments_tbl where comment_id=#{comment_id};")
    int delComment(double comment_id);


    @Select("select count(uid) from user_comments_tbl where uid=#{uid};")
    int commentCount(int uid);

}
