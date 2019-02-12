package com.baizhi.controller;

import com.baizhi.entity.dto.NoteDto;
import com.baizhi.entity.fNote;
import com.baizhi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @RequestMapping("/queryAll")
    public NoteDto queryAll(int page,int rows){
        NoteDto noteByPage = noteService.getNoteByPage(page, rows);
        return noteByPage;
    }

    @RequestMapping("/update")
    public void update(fNote note){
        noteService.updateNote(note);
    }

    @RequestMapping("/remove")
    public void remove(Integer id){
        System.out.println(id+"----controller");
        noteService.removeNote(id);
    }

    @RequestMapping("/regist")
    public void regist(@RequestParam("uploadFile") MultipartFile file, fNote note){
        System.out.println(note);
        System.out.println(file);
        noteService.registNote(note,file);
    }
    @RequestMapping("/queryPubNote")
    public NoteDto queryPubNote(Integer page,Integer rows){
        NoteDto pubNoteByPage = noteService.getPubNoteByPage(page, rows);
        return pubNoteByPage;
    }
    @RequestMapping("/queryById")
    public fNote queryById(Integer id){
        fNote oneNote = noteService.getOneNote(id);
        return oneNote;
    }












}
