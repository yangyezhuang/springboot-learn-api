package com.learn.service;

import com.learn.mapper.AdviceMapper;
import com.learn.model.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Service
public class AdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    /**
     * 添加建议
     *
     * @param advice
     * @return
     */
    public int insertAdvice(Advice advice) {
        // 10位数的时间戳
        Long id = System.currentTimeMillis() / 1000;
        String title = advice.getTitle();
        String content = advice.getContent();
        String name = advice.getName();
        String phone = advice.getPhone();

        // 生成时间
        String time_ = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time_ = dateFormat.format(date);

        return adviceMapper.addAdvice(id, title, content, name, phone, time_);
    }

    /**
     * 删除建议
     *
     * @param id
     * @return
     */
    public int deleteAdvice(Long id) {
        return adviceMapper.delAdvice(id);
    }

    /**
     * 获取全部建议
     *
     * @return
     */
    public List<Advice> listAdvices() {
        return adviceMapper.queryAll();
    }
}
