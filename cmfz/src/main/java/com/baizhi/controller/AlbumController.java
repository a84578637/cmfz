package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.conf.VideoUtil;
import com.baizhi.entity.Album;
import com.baizhi.entity.AlbumPageDto;
import com.baizhi.service.AlbumService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    AlbumService albumService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Autowired
    Logger logger;

    @RequestMapping("/poi")
    public void poi(HttpServletResponse response) {
        List<Album> poi = albumService.getPOI();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("专辑列表", "专辑"), Album.class, poi);

        try {
            String encode = URLEncoder.encode("user.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel");

            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /* //先创建工作本（Workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表(参数为表名
        HSSFSheet sheet = workbook.createSheet("NewSheet");
        //创建日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        //创建单元格格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //单元格设置日期格式
        cellStyle.setDataFormat(dataFormat.getFormat("YYYY年MM月dd日"));
        //设置表某个位子的宽度
        sheet.setColumnWidth(2, 20 * 256);
        //创建表头(参数为下标 0--》表头
        HSSFRow row = sheet.createRow(0);
        //设置表头参数
        String[] strs = {"编号", "名字", "日期"};
        //给表头的每个单元格赋值
        for (int i = 0; i < strs.length; i++) {
            row.createCell(i).setCellValue(strs[i]);
        }
        //开始给表赋值
        for (int i = 0; i < poi.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(poi.get(i).getId());
            //依次赋值，此处省略赋值过程

            //赋值带格式的值
            HSSFCell cell = row1.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(poi.get(i).getPubDate());

        }
        //设置完成后开始往页面输出
        try {
            //先设置响应文件名
            String encode = URLEncoder.encode("album.xls", "UTF-8");
            //设置响应头标签
            response.setHeader("content-disposition","attachment;filename="+encode);
            //设置响应格式
            response.setContentType("application/vnd.ms-excel");
            //往外面写
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }*/


    @RequestMapping("/queryAll")
    public AlbumPageDto queryAll(Integer page, Integer rows) {
        logger.info("收到的分页为：" + page + "--" + rows);
        AlbumPageDto allAlbum = albumService.getAllAlbum(page, rows);
        logger.info("Controller查询成功:" + allAlbum);
        return allAlbum;
    }

    @RequestMapping("/OneAlbum")
    public Album OneAlbum(Integer id) {
        Album one = albumService.getOne(id);
        logger.info("查询成功+：" + one);
        return one;
    }

    @RequestMapping("/regist")
    public void regist(@RequestParam("uploadFile") MultipartFile file, Album album) throws IOException {


        String tail = VideoUtil.getTail(file);


        //文件操作
        StorePath path1 = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), tail, null);
        String path = VideoUtil.getPath(path1);
        //文件操作结束

        album.setCoverImg(path);
        album.setPubDate(new Date());
        albumService.regist(album);
    }

}
