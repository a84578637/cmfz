package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;

import java.io.IOException;
import java.util.List;

public interface BannerService  {
    public BannerPageDto getAllBanner(Integer page,Integer rows);
    public void removeBanner(Integer id) throws IOException;
    public void registBanner(Banner b);
    public void updateBanner(Banner b);
    public List<Banner> getAppBanner(Integer count);
}
