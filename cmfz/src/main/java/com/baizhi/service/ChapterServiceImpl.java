package com.baizhi.service;

import com.baizhi.conf.VideoUtil;
import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
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
    ChapterMapper chapterMapper;
    @Override
    public void regist(Chapter chapter, MultipartFile file) {




        //重命名 、  UUID 、 输出到文件夹
        File newfile = new File("./src/main/webapp/video");
        String uuidName = UUID.randomUUID().toString().replace("-","");
        String path=uuidName+".mp3";

        String realPath = null;
        try {
            realPath = newfile.getCanonicalPath()+"/"+path;
            file.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //计算音频的时间和大小
        String duration = VideoUtil.getDuration(realPath);
        String fileSize = VideoUtil.getFileSize(realPath);
        //初始化
        chapter.setId(UUID.randomUUID().toString().replace("-",""));
        chapter.setDuration(duration);
        chapter.setSize(fileSize);
        chapter.setUploadDate(new Date());
        chapter.setUrl("/video/"+path);
        chapterMapper.insert(chapter);

        System.out.println("---------------------------"+chapter);
    }
}
