package com.baizhi;


import com.alibaba.fastjson.JSON;
import com.baizhi.conf.SnowflakeIdWorker;
import com.baizhi.conf.TestUtil;
import com.baizhi.conf.VideoUtil;
import com.baizhi.entity.*;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.baizhi.mapper")
public class CmfzApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    BannerMapper bannerMapper;
    @Autowired
    UserMapper userMapper;

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

    @Test
    public void Testurl(){

        String duration = VideoUtil.getDuration("F:\\1.11\\C5\\110后期项目\\后期项目\\7. 项目音频\\多智钦仁波切念诵\\多智仁波切念诵.MP3");
        String fileSize = VideoUtil.getFileSize("F:\\1.11\\C5\\110后期项目\\后期项目\\7. 项目音频\\多智钦仁波切念诵\\多智仁波切念诵.MP3");
        logger.info("大小为："+fileSize);
        logger.info("时长为:"+duration);

    }
    @Test
    public void TestSnowFlake(){
        System.out.println("开始："+System.currentTimeMillis());
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 50; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
//            System.out.println(Long.toBinaryString(id));
        }
        System.out.println("结束："+System.currentTimeMillis());
    }

    @Test
    public void TestName(){
        TestUtil testUtil = new TestUtil();
        for (int i = 0; i < 100; i++) {

           // String name = testUtil.getName();
            String chineseName = testUtil.getChineseName();

            logger.info("姓名为："+chineseName);
        }
    }

    @Test
    public void TestDate(){
        TestUtil testUtil = new TestUtil();
        for (int i = 0; i < 100; i++) {

            // String name = testUtil.getName();
            Date date = testUtil.getDate("2018-11-1", "2018-12-25");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String format1 = format.format(date);
            logger.info("时间为："+format1);
        }
    }
    @Test
    public void TestProvince(){
        TestUtil testUtil = new TestUtil();
        for (int i = 0; i < 100; i++) {

            // String name = testUtil.getName();
            String province = testUtil.getProvince();
            logger.info("省为："+province);
        }
    }

    @Test
    public void addUser(){
        TestUtil testUtil = new TestUtil();
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i <1000; i++) {
            User user = new User();
            user.setProvince(testUtil.getProvince());
            user.setName(testUtil.getChineseName());
            user.setDharma(testUtil.getName());
            user.setRegDate(testUtil.getDate("2018-8-1", "2018-12-25"));
            user.setSex(testUtil.getSex());
            user.setStatus("1");
            user.setSign("测试用户");
            user.setCity("测试市区");
            user.setPassword("111111");
            user.setSalt("123456");
             users.add(user);
             if(users.size()>300){
                userMapper.addList(users);
                 users.clear();
                 logger.info("插入----"+i);
             }
        }
        userMapper.addList(users);


    }

    @Test
    public void easygo(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-5d656a5ce51b45779a80fbe8903f8c4c");
                goEasy.publish("140", "Hello, GoEasy!");

    }

    @Test
    public void easyGoUserAdd(){
        Map<String, List<Integer>> userRegist = userService.getUserRegist();


        List<Integer> data = userRegist.get("data");
        String s = JSON.toJSONString(data);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-5d656a5ce51b45779a80fbe8903f8c4c");
        goEasy.publish("user140", s);

    }

    @Test
    public void easyGoUserProvinceAdd(){
        Map<String, List<ProvinceJson>> userProvince = userService.getUserProvince();
        String s = JSON.toJSONString(userProvince);
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-5d656a5ce51b45779a80fbe8903f8c4c");
        goEasy.publish("userProvince",s);

    }

}

