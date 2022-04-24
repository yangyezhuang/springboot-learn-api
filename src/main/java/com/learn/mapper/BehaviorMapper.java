package com.learn.mapper;

import com.learn.model.behavior.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;


@Mapper
public interface BehaviorMapper {

    /**
     * 页面访问次数
     * PV
     */
    @Select("SELECT page_id,count(1) as pvcount " +
            " FROM `user_behavior_tbl`" +
            " GROUP BY page_id" +
            " ORDER BY pvcount;")
    List<PV> getPV();


    /**
     * 用户访问页面次数
     * VU
     */
    @Select("SELECT #{uid} " +
            " as user_id, " +
            " page_id,count(1) as pvcount " +
            " FROM `user_behavior_tbl`" +
            " where user_id = #{uid} " +
            " GROUP BY page_id" +
            " ORDER BY pvcount;")
    List<UV> getUV(int uid);


    /**
     * 总体学习兴趣统计
     */
    @Select("SELECT type," +
            " count(1) as quantity  " +
            " FROM `user_behavior_tbl`" +
            " GROUP BY type" +
            " ORDER BY quantity desc;")
    List<LearningInteres> getLearningInteres();


    /**
     * 总体学习时间段统计
     */
    @Select("SELECT " +
            " CONCAT_WS('-',start_time,end_time) as timeSlot," +
            " count(1) AS quantity" +
            " FROM " +
            " (SELECT  " +
            " SUBSTRING_INDEX(" +
            " SUBSTRING_INDEX(start_time,' ',-1),':',1" +
            " ) AS start_time," +
            " SUBSTRING_INDEX(" +
            " SUBSTRING_INDEX(end_time,' ',-1),':',1" +
            " ) AS end_time" +
            " FROM `user_behavior_tbl`) AS A " +
            " GROUP BY timeSlot ORDER BY timeSlot;")
    List<TimeSlot> getTimeSlot();


    /**
     * 学习专注度
     */
    @Select("SELECT" +
            " ROUND(AVG(if (ss+test_result>100,'100',ss " +
            " + test_result)),2) as Concentration" +
            " FROM (SELECT " +
            " SUBSTRING_INDEX(ss,'.',1)/100 as ss," +
            " test_result  " +
            " FROM (SELECT  " +
            " (UNIX_TIMESTAMP(end_time) " +
            " - UNIX_TIMESTAMP(start_time)) AS ss," +
            " test_result FROM `user_behavior_tbl`" +
            " WHERE user_id = #{uid}) AS A ) as B;")
    int getConcentration(String uid);


    /**
     * 用户学习总时长
     */
    @Select("SELECT ROUND(sum((UNIX_TIMESTAMP(end_time) " +
            " - UNIX_TIMESTAMP(start_time)) / 3600 ),2) AS totalLearningD" +
            " FROM `user_behavior_tbl` WHERE user_id = #{uid};")
    String getAllLearningDuration(int uid);


    /**
     * 用户学习每天时长
     */
    @Select("SELECT SUBSTRING_INDEX(start_time,' ',1) as dateLD ," +
            " COUNT((UNIX_TIMESTAMP(end_time) " +
            " - UNIX_TIMESTAMP(start_time)) / 3600 ) AS learningDuration " +
            " FROM `user_behavior_tbl` WHERE user_id = #{uid} " +
            " GROUP BY SUBSTRING_INDEX(start_time,' ',1) " +
            " ORDER BY learningDuration desc LIMIT 10;")
    List<LearningDuration_To> getLearningDuration(int uid);


    /**
     * 用户时段
     */
    @Select("SELECT " +
            " CONCAT_WS('-',start_time,end_time) as timeSlot," +
            " count(1) AS quantity" +
            " FROM " +
            " (SELECT  " +
            " SUBSTRING_INDEX(" +
            " SUBSTRING_INDEX(start_time,' ',-1),':',1" +
            " ) AS start_time," +
            " SUBSTRING_INDEX(" +
            " SUBSTRING_INDEX(end_time,' ',-1),':',1" +
            " ) AS end_time" +
            " FROM `user_behavior_tbl` " +
            " WHERE user_id = #{uid}) AS A " +
            " GROUP BY timeSlot ORDER BY quantity desc" +
            " LIMIT 10;")
    List<UserTimeSlot> getUserTimeSlot(int uid);


    /**
     * 所有用户列表
     */
    @Select("SELECT * from user_tbl;")
    ArrayList<User> getAllUsers();


    /**
     * 所有内容列表
     */
    @Select("SELECT * from content_tbl;")
    ArrayList<Content> getAllContent();


    /**
     *
     */
    @Select("SELECT * FROM `likes_tbl` WHERE user_id = #{UserID};")
    ArrayList<Likes> findLikesByUser(@Param("UserID") int UserID);


    /**
     * 当前用户喜欢的内容
     */
    @Select("SELECT * FROM `content_tbl` WHERE content_id = #{ContentID};")
    Content findPaperById(@Param("ContentID") int ContentID);


    /**
     *
     */
    @Select("SELECT * from content_tbl order by likeCnt desc;")
    ArrayList<Content> findTopNPapers();


    /**
     *
     */
    @Select("SELECT b.type,count(1) as count" +
            " FROM `likes_tbl`  as a " +
            " INNER JOIN content_tbl as b" +
            " on a.content_id = b.content_id" +
            " WHERE user_id = #{uid} GROUP BY type " +
            " ORDER BY count desc LIMIT 1;")
    Lable userLabelSQL(int uid);

}
