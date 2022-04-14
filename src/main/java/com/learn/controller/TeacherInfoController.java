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
 * @Description: TODO
 * @Date: 2022/4/12 13:18
 * @Author: Yang Yezhuang
 */
@RestController
@RequestMapping("/teacher")
public class TeacherInfoController {
    @Autowired
    TeacherInfoService teacherInfoService;

    @GetMapping("/all")
    public List<TeacherInfo> queryAll() {
        return teacherInfoService.queryAll();
    }

    @GetMapping("/{uid}")
    public TeacherInfo queryById(@PathVariable("uid") Long uid) {
        return teacherInfoService.queryById(uid);
    }
}
