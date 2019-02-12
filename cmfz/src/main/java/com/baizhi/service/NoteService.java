package com.baizhi.service;

import com.baizhi.entity.dto.NoteDto;
import com.baizhi.entity.fNote;
import org.springframework.web.multipart.MultipartFile;

public interface NoteService {
    public NoteDto getNoteByPage(Integer page,Integer rows);
    public void removeNote(Integer id);
    public void registNote(fNote note, MultipartFile file);
    public void updateNote(fNote note);
    public void changeStatus(Integer id,String status);
    public NoteDto getPubNoteByPage(Integer page,Integer rows);
    public fNote getOneNote(Integer id);
}
