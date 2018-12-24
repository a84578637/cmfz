package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget(value = "Chapter")

public class Chapter implements Serializable {
    @Id
    @ExcelIgnore
    private String id;
    @ExcelIgnore
    private String url;
    @Excel(name="标题",width = 15,orderNum="2")
    private String title;
    @Excel(name="大小",width = 15,orderNum="2")
    private String size;
    @ExcelIgnore
    private Integer albumId;
    @Excel(name="时长",width = 15,orderNum="2")
    private String duration;
@JSONField(format = "yyyy-MM-dd HH:mm")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name="上传时间",format = "yyyy-MM-dd",orderNum="2")
    private Date uploadDate;

}