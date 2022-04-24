package com.learn.service;

import com.learn.mapper.TeacherInfoMapper;
import com.learn.model.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Service
public class TeacherInfoService {
    @Autowired
    TeacherInfoMapper teacherInfoMapper;

    /**
     * 获取全部教师信息
     *
     * @return
     */
    public List<TeacherInfo> listTeacherInfo() {
        return teacherInfoMapper.queryAll();
    }

    /**
     * 获取指定教师信息
     *
     * @param uid
     * @return
     */
    public TeacherInfo queryById(Long uid) {
        return teacherInfoMapper.queryById(uid);
    }
}
