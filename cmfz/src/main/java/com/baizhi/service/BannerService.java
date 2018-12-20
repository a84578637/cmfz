package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;

public interface BannerService  {
    public BannerPageDto getAllBanner(Integer page,Integer rows);
    public void removeBanner(Integer id);
    public void registBanner(Banner b);
    public void updateBanner(Banner b);
}
