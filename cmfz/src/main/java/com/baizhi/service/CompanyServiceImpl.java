package com.baizhi.service;

import com.baizhi.entity.dto.CompanyDto;
import com.baizhi.entity.fCompany;
import com.baizhi.entity.fEmployee;
import com.baizhi.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyMapper companyMapper;

    @Override
    public CompanyDto getCompany(Integer page, Integer rows) {
        List<fCompany> fCompanies = companyMapper.selectCompany(page, rows);
        System.out.println(fCompanies);
        Integer total = companyMapper.selectCompanyCount();
        CompanyDto companyDto = new CompanyDto(total, fCompanies);
        return companyDto;
    }

    @Override
    public String addCompany(fCompany company) {
        company.setPerson(0);
        try {
            companyMapper.insert(company);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }


    }

    @Override
    public List<fCompany> getAllComp() {
        List<fCompany> fCompanies = companyMapper.selectAll();

        return fCompanies;
    }
}
