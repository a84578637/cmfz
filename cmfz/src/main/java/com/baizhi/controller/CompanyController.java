package com.baizhi.controller;

import com.baizhi.entity.dto.CompanyDto;
import com.baizhi.entity.fCompany;
import com.baizhi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comp")
public class CompanyController {
    @Autowired
    CompanyService companyService;
@RequestMapping("/getAllComp")
public List<fCompany> getAllComp(){
    List<fCompany> allComp = companyService.getAllComp();
    return allComp;


}
    @RequestMapping("/getComp")
    public CompanyDto getComp(Integer page,Integer rows){

        CompanyDto company = companyService.getCompany(page, rows);

        return company;
    }
    @RequestMapping("/regist")
        public String regist(fCompany company){
        System.out.println(company);
        String s = companyService.addCompany(company);
        return s;
    }
}
