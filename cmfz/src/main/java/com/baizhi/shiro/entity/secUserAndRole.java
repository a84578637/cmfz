package com.baizhi.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class secUserAndRole {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer id;
    private Integer userId;
    private Integer roleId;

}
