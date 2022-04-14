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
 * @Description: NoticeController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/new")
    public Notice queryOne() {
        return noticeService.queryOne();
    }

    @GetMapping("/all")
    public List<Notice> queryAll() {
        return noticeService.queryAll();
    }

    @PostMapping("/add")
    //public int insertNotice(@RequestBody Notice notice) {
    //    log.info(notice.getTitle());

    public String insertNotice(@RequestBody @Valid Notice notice, BindingResult bindingResult) {
        log.info(notice.getTitle() + "" + notice.getText());
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for (ObjectError allError : allErrors) {
                stringBuffer.append(allError.getDefaultMessage() + "，");
            }

            log.info(stringBuffer.toString());
            return stringBuffer.toString();
        } else {
            return String.valueOf(noticeService.insertNotice(notice));
        }

        //return noticeService.insertNotice(notice);
    }

    @DeleteMapping("/del/{id}")
    public String delNotice(@PathVariable("id") long id) {
        noticeService.delNotice(id);
        return "删除成功";
    }

}
