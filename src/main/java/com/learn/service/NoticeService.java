package com.learn.service;

import com.learn.mapper.NoticeMapper;
import com.learn.model.Notice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    /**
     * 查询最新公告
     *
     * @return
     */
    public Notice queryOne() {
        return noticeMapper.queryOne();
    }


    /**
     * 查询全部公告
     *
     * @return
     */
    public List<Notice> queryAll() {
        return noticeMapper.queryAll();
    }


    /**
     * 插入公告
     *
     * @param notice
     * @return
     */
    public int insertNotice(Notice notice) {
        long id = System.currentTimeMillis() / 1000; // 10位数的时间戳
        String title = notice.getTitle();
        String text = notice.getText();

        // 生成时间
        String date_ = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date_ = dateFormat.format(date);

        return noticeMapper.insertNotice(id, title, text, date_);
    }


    /**
     * 根据id删除公告
     *
     * @param id
     * @return
     */
    public int delNotice(long id) {
        return noticeMapper.delNotice(id);
    }
}
