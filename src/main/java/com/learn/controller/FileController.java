package com.learn.controller;

import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Description: FileController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@RestController
public class FileController {

    /**
     * 上传视频
     *
     * @return
     */
    @PostMapping(value = "/upload")
    public Result logUpload(@RequestParam("file") MultipartFile[] files,
                            @RequestParam("title") String title) {
        if (files != null) {
            for (MultipartFile file : files) {
                if (file == null || file.isEmpty()) {
                    return Result.failure(ResultCode.FAILURE);
                }

                String filePath = new File("D:\\Code\\JavaProjects\\SpringBoot\\springboot-learning\\src\\main\\resources\\video\\").getAbsolutePath();
                File fileUpload = new File(filePath);
                if (!fileUpload.exists()) {
                    fileUpload.mkdirs();
                }

                fileUpload = new File(filePath, file.getOriginalFilename());
                if (fileUpload.exists()) {
                    return Result.success("已存在");
                }
                try {
                    file.transferTo(fileUpload);
                    return Result.success("上传成功");
                } catch (IOException e) {
                    return Result.success("上传失败");
                }
            }
        } else {
            return Result.failure(ResultCode.FAILURE);
        }
        return Result.failure(ResultCode.FAILURE);
    }

    /**
     * 上传图片
     *
     * @return
     */
    @PostMapping(value = "/uploadImg")
    public Result uploadImg(@RequestParam("file") MultipartFile[] files,
                            @RequestParam("title") String title) {
        if (files != null) {
            for (MultipartFile file : files) {
                if (file == null || file.isEmpty()) {
                    return Result.failure(ResultCode.FAILURE);
                }

                String filePath = new File("D:\\Code\\JavaProjects\\SpringBoot\\springboot-learning\\src\\main\\resources\\img\\").getAbsolutePath();
                File fileUpload = new File(filePath);
                if (!fileUpload.exists()) {
                    fileUpload.mkdirs();
                }

                fileUpload = new File(filePath, file.getOriginalFilename());
                if (fileUpload.exists()) {
                    return Result.success("已存在");
                }
                try {
                    file.transferTo(fileUpload);
                    return Result.success("上传成功");
                } catch (IOException e) {
                    return Result.success("上传失败");
                }
            }
        } else {
            return Result.failure(ResultCode.FAILURE);
        }
        return Result.failure(ResultCode.FAILURE);
    }

}
