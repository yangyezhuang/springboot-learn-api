package com.learn.service;

import com.learn.mapper.TeacherInfoMapper;
import com.learn.model.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/12 13:17
 * @Author: Yang Yezhuang
 */
@Service
public class TeacherInfoService {
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    public List<TeacherInfo> queryAll() {
        return teacherInfoMapper.queryAll();
    }

    public TeacherInfo queryById(Long uid) {
        return teacherInfoMapper.queryById(uid);
    }
}
