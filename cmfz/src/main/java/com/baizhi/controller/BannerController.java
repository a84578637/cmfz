package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @Autowired
            Logger log;

    @RequestMapping("/queryAll")
    public BannerPageDto queryAll(int page, int rows) {
        BannerPageDto bannerPageDto = bannerService.getAllBanner(page, rows);
        return bannerPageDto;
    }

    @RequestMapping("/update")
    public void update(Banner b) {
        bannerService.updateBanner(b);
    }
    @RequestMapping("/remove")
    public void remove(Integer  bid) throws IOException {

       // String realPath = session.getServletContext().getRealPath("");
       // log.info("地址为:+---------"+canonicalPath);
        bannerService.removeBanner(bid);

    }
    @RequestMapping("/regist")
    public void regist(HttpSession session, @RequestParam("uploadFile") MultipartFile file, Banner banner) throws IOException {
        log.info("进入添加"+file);
        ServletContext ctx = session.getServletContext();
        String realPath = ctx.getRealPath("/img");

        String filename = file.getOriginalFilename();
        String path=realPath + "/" +filename;

        //将内存中的图片输出到相应位置
        File file1 = new File(path);
        file.transferTo(file1);


        //初始化BANNER属性
        banner.setImgPath("/img/"+filename);
        banner.setPubDate(new Date());
        banner.setStatus("N");

        bannerService.registBanner(banner);
        log.info("banner添加成功"+banner);

    }
}
