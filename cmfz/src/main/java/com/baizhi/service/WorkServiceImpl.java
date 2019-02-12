package com.baizhi.service;

import com.baizhi.entity.fCompany;
import com.baizhi.entity.fEmployee;
import com.baizhi.entity.fWork;
import com.baizhi.mapper.CompanyMapper;
import com.baizhi.mapper.EmployeeMapper;
import com.baizhi.mapper.WorkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    CompanyMapper companyMapper;
    @Override
    public String addWork(fEmployee employee, Integer compId) {
        //根据公司ID查询公司所有信息
        fCompany company = new fCompany();
        company.setId(compId);

        fCompany company1 = companyMapper.selectByPrimaryKey(company);
        String reward = company1.getReward();
        fWork fWork = new fWork();
        fWork.setCompid(compId);
        fWork.setEmpid(employee.getId());
        fWork.setOnworktime(new Date());
        fWork.setReward(reward);
        fWork.setSalary(employee.getNsalary());
        workMapper.insert(fWork);

        company1.setPerson(company1.getPerson()+1);
        companyMapper.updateByPrimaryKey(company1);

        fEmployee employee1 = employeeMapper.selectByPrimaryKey(employee.getId());
        employee1.setCompanyId(company1.getId());
        employeeMapper.updateByPrimaryKey(employee1);

        return "success";
    }
}
