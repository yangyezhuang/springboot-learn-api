package com.learn.service;

import com.learn.mapper.AdviceMapper;
import com.learn.model.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/8 16:18
 * @Author: Yang Yezhuang
 */
@Service
public class AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    public int addAdvice(Advice advice) {
        Long id = System.currentTimeMillis() / 1000; // 10位数的时间戳

        String title = advice.getTitle();
        String content = advice.getContent();
        String name = advice.getName();
        String phone = advice.getPhone();

        // 生成时间
        String time = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = dateFormat.format(date);


        return adviceMapper.addAdvice(id, title, content, name, phone, time);
    }

    public int delAdvice(Long id) {
        return adviceMapper.delAdvice(id);
    }

    public List<Advice> queryAll() {
        return adviceMapper.queryAll();
    }
}
