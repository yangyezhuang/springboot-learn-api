package com.learn.mapper;

import com.learn.model.Chapter;
import com.learn.model.Course;
import com.learn.model.Detail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 查询所有课程
    @Select("select * from course_tbl;")
    List<Course> allCourses();

    // 首页热门课程
    @Select("select * from course_tbl limit 5;")
    List<Course> hotCourses();


    // 添加课程
    @Insert("insert into course_tbl(title,info,hour) values(#{title},#{info},#{hour});")
    int addCourse(String title, String info, int hour);


    // 删除课程
    @Delete("delete from course_tbl where id = #{courseID}")
    int delCourse(int courseID);


    // 搜索相关课程
    @Select("select * from course_tbl where title like CONCAT('%',#{keyword},'%');")
    List<Course> search(String keyword);

    @Select("select * from course_tbl where type = #{type};")
    List<Course> category(String type);


    // 课程详情页(TODO)
    //@Select("select * from course_tbl where id = #{course_id}")
    @Select("SELECT a.title,a.info,a.img," +
            "b.chapter_id,b.chapter_title,b.chapter_src " +
            "from course_tbl a JOIN chapter_tbl b " +
            "ON a.id=b.course_id where a.id=#{course_id};")
    List<Detail> getDetail(int course_id);


    // 课程章节
    @Select("SELECT course_id,chapter_id,chapter_title,chapter_src from chapter_tbl where course_id=#{course_id};")
    List<Chapter> getChapter(int course_id);


    // 查找视频
    @Select("select chapter_title,chapter_src from chapter_tbl " +
            "where course_id=#{course_id} and chapter_id=#{chapter_id};")
    Chapter getVideo(int course_id, String chapter_id);

}
