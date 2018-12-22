package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.logging.Logger;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    Logger log;

    @RequestMapping("/regist")
    public void regist(@RequestParam("uploadFile") MultipartFile file, Chapter chapter) {


        chapterService.regist(chapter, file);
        log.info("添加章节成功");

    }

    @RequestMapping("/download")
    public String download(String id, HttpServletResponse response) throws IOException {
        Chapter chapter = chapterService.queryOne(id);
        File file = new File("./src/main/webapp" + chapter.getUrl());

        String canonicalPath = file.getCanonicalPath();


        if (!file.exists()) {
            return "-1";
        }
        response.reset();
        String encode = URLEncoder.encode(chapter.getTitle() + ".mp3", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        try {
            InputStream inStream = new FileInputStream(canonicalPath);
            OutputStream os = response.getOutputStream();

            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buff)) > 0) {
                os.write(buff, 0, len);
            }
            os.flush();
            os.close();

            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }


        return null;
    }
}
