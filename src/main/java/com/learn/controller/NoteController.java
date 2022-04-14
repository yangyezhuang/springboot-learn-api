package com.learn.controller;

import com.learn.model.Note;
import com.learn.service.NoteService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/7 13:58
 * @Author: Yang Yezhuang
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public int addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @DeleteMapping("/del/{note_id}")
    public int addNote(@PathVariable("note_id") Long note_id) {
        return noteService.delNote(note_id);
    }

    @GetMapping("/user/{uid}")
    public List<Note> addNote(@PathVariable("uid") int uid) {
        return noteService.selectNote(uid);
    }

    @GetMapping("/total/user/{uid}")
    public int noteCount(@PathVariable("uid") int uid) {
        return noteService.noteCount(uid);
    }
}
