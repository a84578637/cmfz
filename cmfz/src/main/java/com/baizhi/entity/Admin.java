package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer Id;
    private String username;
    private String password;

}