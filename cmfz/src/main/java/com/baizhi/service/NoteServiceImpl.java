package com.baizhi.service;

import com.baizhi.entity.dto.NoteDto;
import com.baizhi.entity.fNote;
import com.baizhi.mapper.NoteMapper;
import com.baizhi.util.VideoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
@Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    NoteMapper noteMapper;

    @Override
    public NoteDto getNoteByPage(Integer page, Integer rows) {
        Example example = new Example(fNote.class);
        Example.Criteria criteria = example.createCriteria();
        Integer userid =(Integer)SecurityUtils.getSubject().getSession().getAttribute("userid");
        criteria.andEqualTo("userId",userid);
        PageInfo<fNote> pageInfo = PageHelper.startPage(page, rows).setOrderBy("id").doSelectPageInfo(() -> this.noteMapper.selectByExample(example));
        NoteDto noteDto = new NoteDto((int) pageInfo.getTotal(), pageInfo.getList());

        return noteDto;
    }

    @Override
    public void removeNote(Integer id) {
        System.out.println(id);
        fNote fNote = noteMapper.selectByPrimaryKey(id);
        System.out.println(fNote);
        String imgPath = fNote.getImgPath();
        fastFileStorageClient.deleteFile(imgPath);
        noteMapper.deleteByPrimaryKey(id);
        System.out.println("删除成功");
    }

    @Override
    public void registNote(fNote note, MultipartFile file) {
        String tail = VideoUtil.getTail(file);
        StorePath path;
        try {
             path= fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), tail, null);
            String path1=VideoUtil.getPath(path);
            note.setImgPath(path1);
            note.setReleaseTime(new Date());
            note.setStatus("已审核");
            Subject subject = SecurityUtils.getSubject();
            Integer userId = (Integer)subject.getSession().getAttribute("userid");
            note.setUserId(userId);
            noteMapper.insert(note);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    @Override
    public void updateNote(fNote note) {
        noteMapper.updateByPrimaryKey(note);
    }

    @Override
    public void changeStatus(Integer id, String status) {
        fNote fNote = new fNote();
        fNote.setId(id);
        fNote.setStatus("已审核");
        noteMapper.updateByPrimaryKeySelective(fNote);
    }

    @Override
    public NoteDto getPubNoteByPage(Integer page, Integer rows) {
        Example example = new Example(fNote.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status","已审核");
        PageInfo<fNote> pageInfo = PageHelper.startPage(page, rows).setOrderBy("id").doSelectPageInfo(() -> this.noteMapper.selectByExample(example));
        NoteDto noteDto = new NoteDto((int) pageInfo.getTotal(), pageInfo.getList());

        return noteDto;
    }

    @Override
    public fNote getOneNote(Integer id) {
        fNote fNote = noteMapper.selectByPrimaryKey(id);

        return fNote;
    }
}
