package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="menu")
public class Menu implements Serializable {
    @KeySql(useGeneratedKeys = true)
    @Id
    private Integer Id;
    private String url;
    private String iconcls;
    private String title;
    private Integer parentId;
}
