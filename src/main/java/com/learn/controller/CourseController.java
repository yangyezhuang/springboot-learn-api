package com.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.model.Chapter;
import com.learn.model.Course;
import com.learn.model.Detail;
import com.learn.service.CourseService;
import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    /**
     * 查询所有课程
     *
     * @return
     */
    @GetMapping()
    public List<Course> listCourse() {
        List<Course> courses = courseService.listCourses();

        // redis存取
        // stringRedisTemplate.opsForValue().set("courses", String.valueOf(courses));
        // System.out.println(stringRedisTemplate.opsForValue().get("courses"));

        return courses;
    }


    /**
     * 首页热门课程
     *
     * @return
     */
    @GetMapping("/hot")
    public List<Course> listHotCourses() {
        List<Course> hotCourses = courseService.listHotCourses();

        return hotCourses;
    }


    /**
     * 添加课程
     *
     * @return
     */
    @PostMapping("/add")
    public int insertCourse(@RequestBody Course course) {

        return courseService.insertCourse(course);
    }


    /**
     * 删除课程
     * TODO
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return Result.success(ResultCode.SUCCESS);
    }


    /**
     * 搜索相关课程
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public List<Course> listSearchCourses(@PathVariable("keyword") String keyword) {
        List<Course> aboutCourses = courseService.listSearchCourses(keyword);
        // log.info("关键词：" + keyword + "\t相关课程：" + aboutCourses);

        return aboutCourses;
    }


    /**
     * 根据分类获取课程
     *
     * @param type
     * @return
     */
    @GetMapping("/category/{type}")
    public List<Course> listCategoryCourses(@PathVariable("type") String type) {
        return courseService.listCategories(type);
    }


    /**
     * 课程详情
     *
     * @return
     */
    @GetMapping("/{course_id}")
    public List<Detail> listDetail(@PathVariable("course_id") int course_id) {
        List<Detail> detail = courseService.getDetail(course_id);

        return detail;
    }


    /**
     * 课程章节列表
     *
     * @return
     */
    @GetMapping("/chapter/{course_id}")
    public List<Chapter> listChapter(@PathVariable("course_id") int course_id) {
        List<Chapter> chapter = courseService.listChapters(course_id);

        return chapter;
    }


    /**
     * 获取视频资源
     *
     * @return
     */
    @GetMapping("/{course_id}/chapter/{chapter_id}")
    public Chapter getVideo(@PathVariable("course_id") int course_id,
                            @PathVariable("chapter_id") String chapter_id) {

        Chapter chapter = courseService.getVideo(course_id, chapter_id);

        return chapter;
    }

}
