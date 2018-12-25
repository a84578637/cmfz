package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    @ExcelIgnore
    private Integer id;
    @Excel(name="手机",width=25,needMerge = true)
    private String phone;
    @Excel(name="姓名",width=25,needMerge = true)
    private String name;
    @Excel(name="头像",type=2,width=30,height = 30,needMerge = true)
    private String headPic;
    @Excel(name="法号",width=25,needMerge = true)
    private String dharma;
    @Excel(name="性别",width=25,needMerge = true)
    private String sex;
    @Excel(name="省",width=25,needMerge = true)
    private String province;
    @Excel(name="市",width=25,needMerge = true)
    private String city;
    @Excel(name="个性签名",width=25,needMerge = true)
    private String sign;
    @Excel(name="密码",width=25,needMerge = true)
    private String password;
    @Excel(name="盐",width=25,needMerge = true)
    private String salt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JSONField(format = "yyyy-MM-dd HH:mm")
    @Excel(name = "创建时间",format = "yyyy-MM-dd",width=20,needMerge = true)
    private Date regDate;
    @Excel(name="状态",width=25,needMerge = true)
    private String status;


}