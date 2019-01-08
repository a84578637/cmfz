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
public class secRole {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer roleId;
    private String roleName;
    private Date createdDate;
    private Date updateTime;
}
