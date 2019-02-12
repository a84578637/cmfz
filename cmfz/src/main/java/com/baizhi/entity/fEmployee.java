package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="f_employee")
@ExcelTarget(value = "f_Employee")
public class fEmployee {
    @KeySql(useGeneratedKeys = true)
    @Id
    @ExcelIgnore
    private Integer id;
    @Excel(name="用户名",orderNum="1",width=25,needMerge = true)
    private String name;
    @Excel(name="年龄",orderNum="1",width=25,needMerge = true)
    private Integer age;
    @Excel(name="手机号",orderNum="1",width=25,needMerge = true)
    private String phone;
    @Excel(name="性别",orderNum="1",width=25,needMerge = true)
    private String sex;
    @Excel(name="籍贯",orderNum="1",width=25,needMerge = true)
    private String address;

    @Excel(name="长短工",orderNum="1",width=25,needMerge = true)
    private String style;
    @Excel(name="期望薪资",orderNum="1",width=25,needMerge = true)
    private String nsalary;
    @Excel(name="期望城市",orderNum="1",width=25,needMerge = true)
    private String ncity;
    @Excel(name="期望工作",orderNum="1",width=25,needMerge = true)
    private String nwork;
    @Excel(name="期望工时",orderNum="1",width=25,needMerge = true)
    private Integer worktime;
    @ExcelIgnore
    private Integer userId;
    @ExcelIgnore
    private Integer companyId;
}
