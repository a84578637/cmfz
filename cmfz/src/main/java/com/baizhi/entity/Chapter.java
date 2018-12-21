package com.baizhi.entity;

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
public class Chapter implements Serializable {
    @Id
    private Integer id;

    private String url;

    private String title;

    private String size;

    private Integer albumId;

    private String duration;
@JSONField(format = "yyyy-MM-dd HH:mm")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date uploadDate;

}