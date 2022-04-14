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
 * @Description: CourseController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
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
    @GetMapping("/all")
    public List<Course> findAll() {
        List<Course> courses = courseService.findAll();
        //log.info("全部课程：" + courses.toString());

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
    public List<Course> hotCourses() {
        List<Course> hotCourses = courseService.hotCourses();
        //log.info("热门课程：" + hotCourses);

        return hotCourses;
    }


    /**
     * 添加课程
     *
     * @return
     */
    @PostMapping("/add")
    public int addCourse(@RequestBody Course course) {

        return courseService.addCourse(course);
    }


    /**
     * 删除课程
     * TODO
     *
     * @param jsonParam
     * @return
     */
    @DeleteMapping("/del")
    public Result delCourse(@RequestBody JSONObject jsonParam) {
        String course_ID = jsonParam.get("courseID").toString();
        int courseID = Integer.parseInt(course_ID);

        courseService.delCouse(courseID);
        return Result.success(ResultCode.SUCCESS);
    }


    /**
     * 搜索相关课程
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public List<Course> search(@PathVariable("keyword") String keyword) {
        List<Course> aboutCourses = courseService.search(keyword);
        //log.info("关键词：" + keyword + "\t相关课程：" + aboutCourses);

        return aboutCourses;
    }

    /**
     * 根据分类获取课程
     *
     * @param type
     * @return
     */
    @GetMapping("/category/{type}")
    public List<Course> category(@PathVariable("type") String type) {
        return courseService.category(type);
    }


    /**
     * 课程详情
     *
     * @return
     */
    @GetMapping("/detail/{course_id}")
    public List<Detail> getDetail(@PathVariable("course_id") int course_id) {
        List<Detail> detail = courseService.getDetail(course_id);
        //log.info("课程详情：" + detail);

        return detail;
    }


    /**
     * 课程章节列表
     *
     * @return
     */
    @GetMapping("/chapter/{course_id}")
    public List<Chapter> getChapterList(@PathVariable("course_id") int course_id) {
        List<Chapter> chapter = courseService.getChapterList(course_id);
        //log.info("章节列表：" + chapter);

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
        //log.info("视频资源：" + chapter);

        return chapter;
    }

}
