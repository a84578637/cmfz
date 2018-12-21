package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumPageDto;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
@Autowired
Logger logger;


    @RequestMapping("/queryAll")
    public AlbumPageDto queryAll(Integer page ,Integer rows){
        logger.info("收到的分页为："+page+"--"+rows);
        AlbumPageDto allAlbum = albumService.getAllAlbum(page, rows);
        logger.info("Controller查询成功:"+allAlbum);
        return allAlbum;
    }

    @RequestMapping("/OneAlbum")
    public Album OneAlbum(Integer id){
        Album one = albumService.getOne(id);
        logger.info("查询成功+："+one);
        return one;
    }
    @RequestMapping("/regist")
    public void regist(HttpSession session, @RequestParam("uploadFile") MultipartFile file, Album album) throws IOException {

        //文件操作
        logger.info("进入添加"+file);
        System.out.println("1");
        ServletContext ctx = session.getServletContext();

        String realPath = ctx.getRealPath("/img");

        String uuidName = UUID.randomUUID().toString().replace("-","")+".jpg";
        String path=realPath + "/" +uuidName;

        logger.info("地址："+path);

        File file1 = new File(path);

        file.transferTo(file1);
        //文件操作结束



        //初始化BANNER属性


        albumService.regist(album);


    }

}
