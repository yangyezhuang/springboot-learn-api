package com.learn.service;

import com.learn.mapper.NoteMapper;
import com.learn.model.Note;
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
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    /**
     * 添加笔记
     *
     * @param note
     * @return
     */
    public int insertNote(Note note) {
        int uid = note.getUid();
        String notes = note.getNote();
        // 10位数的时间戳
        long note_id = System.currentTimeMillis() / 1000;

        // 生成时间
        String time = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = dateFormat.format(date);

        return noteMapper.insert(note_id, uid, notes, time);
    }


    /**
     * 删除笔记
     *
     * @param note_id
     * @return
     */
    public int deleteNote(Long note_id) {
        return noteMapper.delete(note_id);
    }


    /**
     * @param uid
     * @return
     */
    public List<Note> selectNote(int uid) {
        return noteMapper.queryAll(uid);
    }


    /**
     * 统计笔记总数
     *
     * @param uid
     * @return
     */
    public int countNotes(int uid) {
        return noteMapper.noteCount(uid);
    }
}
