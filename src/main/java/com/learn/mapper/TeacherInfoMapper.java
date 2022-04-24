package com.learn.mapper;

import com.learn.model.TeacherInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/12 13:15
 * @Author: Yang Yezhuang
 */
@Mapper
public interface TeacherInfoMapper {

    @Select("select a.uid,a.info,a.img,b.username " +
            "from tch_info_tbl a join user_tch_tbl b " +
            "on a.uid=b.uid where a.uid=b.uid;")
    List<TeacherInfo> queryAll();


    @Select("select a.uid,a.info,a.img,b.username " +
            "from tch_info_tbl a join user_tch_tbl b " +
            "on a.uid=b.uid where a.uid=#{uid};")
    TeacherInfo queryById(Long uid);
}
