package com.baizhi;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.entity.BannerPageDto;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.baizhi.mapper")
public class CmfzApplicationTests {

    @Autowired
    BannerMapper bannerMapper;
    Logger logger = Logger.getLogger("a");

    @Autowired
    AlbumMapper albumMapper;

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

    @Test
    public void TestMBG(){
//        List<Album> albums = albumMapper.selectAllAlbum();
//        for (Album album : albums) {
//            logger.info("新消息：_--------------"+album);
//        }

    }
    //一对多查询时会对结果进行折叠，分页会错误。
    @Test
    public void TestAlbumPage(){
//        PageInfo<Album> objectPageInfo = PageHelper.startPage(1, 1).doSelectPageInfo(() -> albumMapper.selectAllAlbum());
//        logger.info("共有几条数据:"+objectPageInfo.getList().size());
//        PageInfo<Album> pageInfo = PageHelper.startPage(1, 2).doSelectPageInfo(() -> albumMapper.selectAllAlbum());
//        List<Album> list = pageInfo.getList();
//
//        logger.info("第二次共有"+list.size());

    }

    @Test
    public void TestAlbumPage2(){
        List<Album> albums = albumMapper.selectAllAlbum(1, 1);
        logger.info("第一次查询一个，结果又："+albums);
        List<Album> albums1 = albumMapper.selectAllAlbum(1, 2);
        logger.info("第二次查询，结果有："+albums1);


    }

}

