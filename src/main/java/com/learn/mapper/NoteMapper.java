package com.learn.mapper;

import com.learn.model.Note;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/7 13:57
 * @Author: Yang Yezhuang
 */
@Mapper
public interface NoteMapper {
    @Insert("Insert into note_tb (note_id,uid,note,date) values(#{note_id},#{uid},#{notes},#{time});")
    int insert(Long note_id, int uid, String notes, String time);

    @Delete("DELETE FROM note_tb WHERE note_id=#{note_id};")
    int delete(Long note_id);

    @Select("select * from note_tb where uid = #{uid};")
    List<Note> queryAll(int uid);

    @Select("select count(uid) from note_tb where uid = #{uid};")
    int noteCount(int uid);
}
