package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class fWork {

    @KeySql(useGeneratedKeys = true)
    @Id
        private Integer id;
    private Integer empid;
    private Integer compid;
    private String salary;
    private Date onworktime;
    private String reward;

}
