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
public class secPermission {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer permissionId;
    private String permissionName;
    private Date createdTime;
    private Date updateTime;
}
