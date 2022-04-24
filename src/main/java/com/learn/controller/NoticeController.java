package com.learn.controller;

import com.learn.model.Notice;
import com.learn.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    /**
     * 查找最新公告
     *
     * @return
     */
    @GetMapping("/new")
    public Notice getNewNotice() {
        return noticeService.getNewNotice();
    }


    /**
     * 查找全部公告
     *
     * @return
     */
    @GetMapping()
    public List<Notice> listNotices() {
        return noticeService.listNotices();
    }


    /**
     * 添加公告
     *
     * @param notice
     * @param bindingResult
     * @return
     */
    @PostMapping()
    public String insertNotice(@RequestBody @Valid Notice notice, BindingResult bindingResult) {
        log.info(notice.getTitle() + "" + notice.getText());
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (ObjectError allError : allErrors) {
                stringBuffer.append(allError.getDefaultMessage() + "，");
            }
            // log.info(stringBuffer.toString());
            return stringBuffer.toString();
        } else {
            return String.valueOf(noticeService.insertNotice(notice));
        }
    }


    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteNotice(@PathVariable("id") long id) {
        noticeService.deleteNotice(id);
        return "删除成功";
    }

}
