package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
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
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    Logger log;

    @RequestMapping("/regist")
    public void regist(@RequestParam("uploadFile") MultipartFile file, Chapter chapter) throws IOException {

        chapterService.regist(chapter,file);
        log.info("添加章节成功");

    }

    @RequestMapping("/download")
    public String download(String id, HttpServletResponse response) throws IOException {
        Chapter chapter = chapterService.queryOne(id);
        String url = chapter.getUrl();
        String path = url.substring(url.indexOf("/")+1);
        String group = url.replace("/"+path, "");
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, new DownloadByteArray());



        response.reset();

        String encode = URLEncoder.encode(chapter.getTitle() + ".mp3", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(bytes);

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
