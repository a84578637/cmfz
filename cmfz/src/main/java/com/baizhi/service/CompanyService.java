package com.baizhi.service;

import com.baizhi.entity.dto.CompanyDto;
import com.baizhi.entity.dto.EmployeeDto;
import com.baizhi.entity.fCompany;
import com.baizhi.entity.fEmployee;

import java.util.List;

public interface CompanyService {
    public CompanyDto getCompany(Integer page, Integer rows);
    public String addCompany(fCompany company);
    public List<fCompany> getAllComp();
}
