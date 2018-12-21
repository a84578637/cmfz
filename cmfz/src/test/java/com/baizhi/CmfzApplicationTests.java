package com.baizhi;

import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;
import com.baizhi.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {

    @Autowired
    BannerMapper bannerMapper;
    Logger logger = Logger.getLogger("a");

    @Test
    //PageHelper
    public void contextLoads() {/*
    // List<Banner> banners = bannerMapper.selectAll();
      //  logger.info("查询到的"+banners);*/
        PageInfo<Banner> pageInfo = PageHelper.startPage(2, 3).setOrderBy("id").doSelectPageInfo(() -> this.bannerMapper.selectAll());
        long total = pageInfo.getTotal();
        List<Banner> list = pageInfo.getList();
        list.forEach(System.out::println);
        BannerPageDto bannerPageDto = new BannerPageDto((int )total,list);
        logger.info("分页信息"+ bannerPageDto.toString());


    }


    @Test
    public void Test(){
        List<Banner> banners = bannerMapper.selectByRowBounds(new Banner(), new RowBounds(0, 3));
        for (Banner banner : banners) {
            logger.info("Banner查出来的数据"+banner);
        }

        logger.info("有几条数据？:"+banners.size());
    }



}

