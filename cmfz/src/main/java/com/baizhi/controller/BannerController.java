package com.baizhi.controller;

import com.baizhi.conf.VideoUtil;
import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;
import com.baizhi.service.BannerService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
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

        bannerService.removeBanner(bid);


    }
    @RequestMapping("/regist")
    public void regist( @RequestParam("uploadFile") MultipartFile file, Banner banner) throws IOException {

        String tail = VideoUtil.getTail(file);

        StorePath path1 = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), tail, null);
        String path = VideoUtil.getPath(path1);
        //初始化BANNER属性
        banner.setImgPath(path);
        banner.setPubDate(new Date());
        banner.setStatus("N");
        bannerService.registBanner(banner);
        log.info("banner添加成功"+banner);

    }
}
