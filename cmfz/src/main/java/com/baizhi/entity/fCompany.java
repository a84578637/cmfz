package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="f_company")
public class fCompany {
    @Id
    @KeySql(useGeneratedKeys = true)

    private Integer id;
    private String contact;
    private String phone;
    private String address;
    private Integer nperson;
    private Integer person;
    private String nsex;
    private String motoley;
    private String work;
    private String salary;
    private Integer worktime;
    private String city;
    private String style;
    private String reward;

}
