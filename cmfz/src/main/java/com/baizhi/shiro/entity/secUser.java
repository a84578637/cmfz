package com.baizhi.shiro.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class secUser {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer userId;
    private String userName;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date createdTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date loginTime;
    private String salt;
    private String userPhone;


}
