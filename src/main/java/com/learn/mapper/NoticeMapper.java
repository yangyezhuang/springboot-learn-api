package com.learn.mapper;

import com.learn.model.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    @Select("select * from notice_tbl order by id desc limit 1;")
    Notice queryOne();

    @Select("select * from notice_tbl;")
    List<Notice> queryAll();

    @Insert("insert into notice_tbl(id,title,text,date) values(#{id},#{title},#{text},#{date_});")
    int insertNotice(long id, String title, String text, String date_);

    @Delete("delete from notice_tbl where id=#{id};")
    int delNotice(long id);

}
