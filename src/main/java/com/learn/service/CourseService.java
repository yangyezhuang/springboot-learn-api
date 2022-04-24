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

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "course")
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;


    /**
     * 首页热门课程
     *
     * @return
     */
    public List<Course> listHotCourses() {
        return courseMapper.hotCourses();
    }


    /**
     * 全部课程
     *
     * @return
     */
    // @Cacheable(key = "'allCourses'", unless = "#result==null")
    public List<Course> listCourses() {
        return courseMapper.allCourses();
    }


    /**
     * 搜索相关课程
     *
     * @param keyword
     * @return
     */
    // @Cacheable(key = "#keyword", unless = "#result==null ")
    public List<Course> listSearchCourses(String keyword) {
        return courseMapper.search(keyword);
    }


    /**
     * 根据分类获取课程
     *
     * @param type
     * @return
     */
    public List<Course> listCategories(String type) {
        return courseMapper.category(type);
    }


    /**
     * 课程详情
     *
     * @param id
     * @return
     */
    public List<Detail> getDetail(int id) {
        return courseMapper.getDetail(id);
    }


    /**
     * 课程详情
     *
     * @param id
     * @return
     */
    public List<Chapter> listChapters(int id) {
        return courseMapper.getChapter(id);
    }


    /**
     * 获取视频资源
     *
     * @param course_id
     * @param chapter_id
     * @return
     */
    public Chapter getVideo(int course_id, String chapter_id) {
        return courseMapper.getVideo(course_id, chapter_id);
    }


    /**
     * 添加课程
     *
     * @param course
     * @return
     */
    public int insertCourse(Course course) {
        String title = course.getTitle();
        String info = course.getInfo();
        int hour = course.getHour();

        return courseMapper.addCourse(title, info, hour);
    }


    /**
     * 删除课程
     *
     * @param courseID
     */
    public void deleteCourse(int courseID) {
        courseMapper.delCourse(courseID);
    }

}
