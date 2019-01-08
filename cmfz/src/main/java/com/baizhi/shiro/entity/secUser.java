package com.baizhi.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Date createdTime;
    private Date updateTime;
    private String salt;

}
