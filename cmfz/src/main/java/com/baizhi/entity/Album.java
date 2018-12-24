package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "Album")
public class Album implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name="专辑名",orderNum="1",width=25,needMerge = true)
    private String title;
    @Excel(name="作者",orderNum="3",width=15,needMerge = true)
    private String author;
    @Excel(name="播音",orderNum = "3",width=15,needMerge = true)
    private String broadcast;
    @Excel(name="评分",orderNum = "3",width=15,needMerge = true)
    private Double score;
    @Excel(name="音频数量",orderNum = "3",width=15,needMerge = true)
    private Integer count;
    @Excel(name="简介",orderNum = "3",width=15,needMerge = true)
    private String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Excel(name = "创建时间",format = "yyyy-MM-dd",width=20,needMerge = true)
    private Date pubDate;



    @Excel(name="背景图片",type=2,width=30,height = 30,needMerge = true)

    private String coverImg;








    @ExcelCollection(name="音频",orderNum="4")
    private List<Chapter> children;
}