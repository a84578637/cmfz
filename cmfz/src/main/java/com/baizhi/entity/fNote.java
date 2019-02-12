package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class fNote {
    @KeySql(useGeneratedKeys = true)
    @Id

    private Integer id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date releaseTime;
    private String content;
    private String status;
    private Integer userId;
    private String imgPath;
}
