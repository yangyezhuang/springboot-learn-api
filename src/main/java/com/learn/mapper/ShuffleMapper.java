package com.learn.mapper;

import com.learn.model.Shuffle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShuffleMapper {

    @Select("select id,url from shuffle_tbl;")
    List<Shuffle> queryAll();
}
