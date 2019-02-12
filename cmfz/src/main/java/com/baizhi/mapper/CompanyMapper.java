package com.baizhi.mapper;

import com.baizhi.entity.fCompany;
import com.baizhi.entity.fEmployee;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CompanyMapper extends Mapper<fCompany> {
    public List<fCompany> selectCompany(@Param("page") Integer page, @Param("rows") Integer rows);
    public Integer selectCompanyCount();
}
