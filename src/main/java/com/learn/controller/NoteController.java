package com.learn.controller;

import com.learn.model.Note;
import com.learn.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    /**
     * 添加笔记
     *
     * @param note
     * @return
     */
    @PostMapping()
    public int insertNote(@RequestBody Note note) {
        return noteService.insertNote(note);
    }

    /**
     * 删除笔记
     *
     * @param note_id
     * @return
     */
    @DeleteMapping("/{note_id}")
    public int deleteNote(@PathVariable("note_id") Long note_id) {
        return noteService.deleteNote(note_id);
    }

    /**
     * 查询用户所有笔记
     *
     * @param uid
     * @return
     */
    @GetMapping("/user/{uid}")
    public List<Note> listNotes(@PathVariable("uid") int uid) {
        return noteService.selectNote(uid);
    }

    /**
     * 用户笔记数量
     *
     * @param uid
     * @return
     */
    @GetMapping("/total/user/{uid}")
    public int countNotes(@PathVariable("uid") int uid) {
        return noteService.countNotes(uid);
    }
}
