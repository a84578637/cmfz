package com.baizhi.service;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.dto.EmployeeDto;
import com.baizhi.entity.fCompany;
import com.baizhi.entity.fEmployee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    public EmployeeDto getUnused(Integer page, Integer rows);
    public EmployeeDto getUsed(Integer page, Integer rows);
    public String delUser(Integer id);
    public List<fEmployee> getPOI();
    public String addEmp(fEmployee employee);
    public Map<String , List<ProvinceJson>> getEmpProvince();
    public fEmployee getByPhone(String phone);
    public String updateEmp(fEmployee fEmployee);
    public String searchEmpByPhone(String phone);

}
