package com.baizhi.controller;

import com.baizhi.entity.BannerPageDto;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/banner")
public class BannerController  {
    @Autowired
    BannerService bannerService;
    Logger log = Logger.getLogger("Banner");
    @RequestMapping("/queryAll")
    public BannerPageDto queryAll(int page, int rows){
        BannerPageDto bannerPageDto = bannerService.getAllBanner(page, rows);
        log.info("查询成功："+bannerPageDto);
        return bannerPageDto;
    }
}
