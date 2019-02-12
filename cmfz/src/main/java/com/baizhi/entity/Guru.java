package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guru implements Serializable {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer id;

    private String dharma;

    private String headPic;

    private String status;


}