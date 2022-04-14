package com.learn.service;

import com.learn.mapper.NoteMapper;
import com.learn.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/7 13:59
 * @Author: Yang Yezhuang
 */
@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    public int addNote(Note note) {
        int uid = note.getUid();
        String notes = note.getNote();
        long note_id = System.currentTimeMillis() / 1000; // 10位数的时间戳

        // 生成时间
        String time = "";
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = dateFormat.format(date);

        return noteMapper.insert(note_id, uid, notes, time);
    }

    public int delNote(Long note_id) {
        return noteMapper.delete(note_id);
    }

    public List<Note> selectNote(int uid) {
        return noteMapper.queryAll(uid);
    }

    public int noteCount(int uid) {
        return noteMapper.noteCount(uid);
    }
}
