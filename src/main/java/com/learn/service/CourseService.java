package com.learn.service;

import com.learn.mapper.CourseMapper;
import com.learn.model.Chapter;
import com.learn.model.Course;
import com.learn.model.Detail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = "course")
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;


    // 首页热门课程
    public List<Course> hotCourses() {
        return courseMapper.hotCourses();
    }


    // 全部课程
    //@Cacheable(key = "'allCourses'", unless = "#result==null")
    public List<Course> findAll() {
        return courseMapper.allCourses();
    }


    // 搜索相关课程
    //@Cacheable(key = "#keyword", unless = "#result==null ")
    public List<Course> search(String keyword) {
        return courseMapper.search(keyword);
    }

    // 根据分类获取课程
    public List<Course> category(String type){
        return courseMapper.category(type);
    }


    // 课程详情
    public List<Detail> getDetail(int id) {
        return courseMapper.getDetail(id);
    }


    // 课程详情
    public List<Chapter> getChapterList(int id) {
        return courseMapper.getChapter(id);
    }


    // 获取视频资源
    public Chapter getVideo(int course_id, String chapter_id) {
        return courseMapper.getVideo(course_id, chapter_id);
    }


    // 添加课程
    public int addCourse(Course course) {
        String title = course.getTitle();
        String info = course.getInfo();
        int hour = course.getHour();
        log.info("title:" + title + "info:" + info + "hour:" + hour);

        return courseMapper.addCourse(title, info, hour);
    }


    // 删除课程
    public void delCouse(int courseID) {
        courseMapper.delCourse(courseID);
    }

}
