package com.baizhi.service;

import com.baizhi.conf.VideoUtil;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService{
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    ChapterMapper chapterMapper;
    @Override
    public void regist(Chapter chapter, MultipartFile file) {

        String tail = VideoUtil.getTail(file);
        StorePath path = null;
        try {
            path = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), tail, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path1 = VideoUtil.getPath(path);
        chapter.setUrl(path1);


        //重命名 、  UUID 、 输出到文件夹
        File newfile = new File("./src/main/webapp/video");
        String uuidName = UUID.randomUUID().toString().replace("-","");
        String Lpath=uuidName+".mp3";

        String realPath = null;
        File file1=null;
        try {
            realPath = newfile.getCanonicalPath()+"/"+Lpath;
             file1 = new File(realPath);
            file.transferTo(file1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //计算音频的时间和大小
        String duration = VideoUtil.getDuration(realPath);
        String fileSize = VideoUtil.getFileSize(realPath);

        //初始化
        chapter.setDuration(duration);
        chapter.setSize(fileSize);
        chapter.setId(UUID.randomUUID().toString().replace("-",""));
        chapter.setUploadDate(new Date());
        file1.delete();
        chapterMapper.insert(chapter);

        System.out.println("---------------------------"+chapter);
    }

    @Override
    public Chapter queryOne(String id) {
        Chapter chapter = chapterMapper.selectByPrimaryKey(id);
        return chapter;
    }
}
