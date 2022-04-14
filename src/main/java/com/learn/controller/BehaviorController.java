package com.learn.controller;

import com.learn.service.BehaviorService;
import com.learn.util.result.Result;
import com.learn.util.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: BehaviorController
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@Slf4j
@RestController
@RequestMapping("/behavior")
public class BehaviorController {

    @Autowired
    private BehaviorService behaviorService;

    /**
     * PV：Page View，页面访问次数。
     *
     * @return
     */
    @PostMapping("/pageView")
    public Result getUser() {
        try {
            return Result.success(behaviorService.PVService());
        } catch (Exception e) {
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * UV：Unique Visitor
     * 页面独立的访问次数S
     * 通过Cookie区分不同的人
     * 用户访问页面次数
     */
    @PostMapping("/uniqueVisitor/{uid}")
    public Result getUV(@PathVariable("uid") int uid) {
        try {
            return Result.success(behaviorService.UVService(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 总体学习兴趣统计
     *
     * @param
     * @return
     */
    @PostMapping("/learnInteres")
    public Result getLearningInteres() {
        try {
            return Result.success(behaviorService.learningInteres());
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 总体学习时段
     */
    @PostMapping("/timeSlot")
    public Result getTimeSlot() {
        try {
            return Result.success(behaviorService.getTimeSlot());
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 学习专注度
     */
    @PostMapping("/concentration/{uid}")
    public Result getConcentration(@PathVariable("uid") String uid) {
        try {
            return Result.success(behaviorService.ConcentrationService(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 用户学习时长
     */
    @PostMapping("/learningDuration/{uid}")
    public Result getLearningDuration(@PathVariable("uid") int uid) {
        try {
            return Result.success(behaviorService.LearningDurationService(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 用户学习时长
     */
    @PostMapping("/userTimeSlot/{uid}")
    public Result getUserTimeSlot(@PathVariable("uid") int uid) {
        try {
            return Result.success(behaviorService.userTimeSlotService(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 内容推荐
     */
    @PostMapping("/centont/{uid}")
    public Result getCentont(@PathVariable("uid") int uid) {
        try {
            return Result.success(behaviorService.centont(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }


    /**
     * 类型推荐
     *
     * @param
     * @return
     */
    @PostMapping("/userlabel/{uid}")
    public Result getuserLabel(@PathVariable("uid") int uid) {
        try {
            return Result.success(behaviorService.userlabel(uid));
        } catch (Exception e) {
            log.error("获取失败 :{}", e.getMessage());
            return Result.failure(ResultCode.FAILURE, e.getMessage());
        }
    }
}
