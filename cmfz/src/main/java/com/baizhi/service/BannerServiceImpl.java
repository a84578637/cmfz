package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;
import com.baizhi.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
Logger log = Logger.getLogger("Banner");
    @Override
    public BannerPageDto getAllBanner(Integer page,Integer rows) {
        PageInfo<Banner> pageInfo = PageHelper.startPage(page, rows).setOrderBy("id").doSelectPageInfo(() -> this.bannerMapper.selectAll());
          log.info("BannerServiceImpl中，getAllBanner查询到结果："+pageInfo);
        BannerPageDto bannerPageDto = new BannerPageDto((int )pageInfo.getTotal(),pageInfo.getList());
        return bannerPageDto;
    }
    @Override
    public void removeBanner(Integer id) {
        log.info("BannerServiceImpl中，删除的ID为："+id);
        bannerMapper.deleteByPrimaryKey(id);
        log.info("BannerServiceImpl中，删除成功！");
    }

    @Override
    public void registBanner(Banner b) {
        log.info("BannerServiceImpl中，添加Banner为："+b);
        bannerMapper.insert(b);
        log.info("BannerServiceImpl中，添加完成");
    }

    @Override
    public void updateBanner(Banner b) {
        log.info("BannerServiceImpl中，修改Banner为："+b);
        bannerMapper.updateByPrimaryKey(b);
        log.info("BannerServiceImpl中，修改完成");
    }
}
