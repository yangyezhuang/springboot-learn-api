package com.learn.controller;

import com.learn.model.TeacherInfo;
import com.learn.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    TeacherInfoService teacherInfoService;

    /**
     * 获取教师信息列表
     *
     * @return
     */
    @GetMapping()
    public List<TeacherInfo> listTeacherInfo() {
        return teacherInfoService.listTeacherInfo();
    }

    /**
     * 获取指定教师信息
     *
     * @param uid
     * @return
     */
    @GetMapping("/{uid}")
    public TeacherInfo queryById(@PathVariable("uid") Long uid) {
        return teacherInfoService.queryById(uid);
    }
}
