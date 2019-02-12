package com.baizhi.service;

import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.dto.EmployeeDto;
import com.baizhi.entity.fEmployee;
import com.baizhi.mapper.EmployeeMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;
    @Override
    public EmployeeDto getUnused(Integer page, Integer rows) {
        List<fEmployee> fEmployees = employeeMapper.selectUnused(page, rows);

        return setDto(null, fEmployees);
    }

    @Override
    public EmployeeDto getUsed(Integer page, Integer rows) {
        Integer userId=(Integer)SecurityUtils.getSubject().getSession().getAttribute("userid");
        List<fEmployee> fEmployees = employeeMapper.selectUsed(page, rows, userId);

        return setDto(userId,fEmployees);
    }

    @Override
    public String delUser(Integer id) {
        fEmployee fEmployee = new fEmployee();
        fEmployee.setId(id);
        try {
            employeeMapper.delete(fEmployee);
                return "success";
                }catch (Exception e){
            e.printStackTrace();
            return "false";

        }
    }

    @Override
    public List<fEmployee> getPOI() {
        fEmployee fEmployee = new fEmployee();
        fEmployee.setUserId(0);
        List<com.baizhi.entity.fEmployee> employees = employeeMapper.select(fEmployee);
        return employees;
    }

    @Override
    public String addEmp(fEmployee employee) {
        try {
            Integer userid=(Integer)SecurityUtils.getSubject().getSession().getAttribute("userid");
            employee.setUserId(userid);
            int insert = employeeMapper.insert(employee);
            return "success";

                }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    @Override
    public Map<String, List<ProvinceJson>> getEmpProvince() {
        List<ProvinceJson> empProvince = employeeMapper.getEmpProvince();
        List<ProvinceJson> empProvinceMan = employeeMapper.getEmpProvinceMan();
        List<ProvinceJson> empProvinceWoMan = employeeMapper.getEmpProvinceWoMan();
        HashMap<String, List<ProvinceJson>> map = new HashMap<>();
        map.put("data",empProvince);
        map.put("woman",empProvinceWoMan);
        map.put("man",empProvinceMan);
        return map;
    }

    @Override
    public fEmployee getByPhone(String phone) {
        fEmployee fEmployee = new fEmployee();
        fEmployee.setPhone(phone);
        com.baizhi.entity.fEmployee fEmployee1 = employeeMapper.selectOne(fEmployee);
        return fEmployee1;
    }

    @Override
    public String updateEmp(fEmployee fEmployee) {
        int i = employeeMapper.updateByPrimaryKey(fEmployee);
        return "success";
    }

    @Override
    public String searchEmpByPhone(String phone) {
        fEmployee employee = new fEmployee();
        employee.setPhone(phone);
        fEmployee fEmployee = employeeMapper.selectOne(employee);
        if(fEmployee==null){
            return "false";
        }else{
            return "success";
        }
    }

    public EmployeeDto setDto(Integer userId,List<fEmployee> rows){
        fEmployee employee = new fEmployee();
        int total = employeeMapper.getEmpCount(userId);
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setTotal(total);
        employeeDto.setRows(rows);
    return employeeDto;
    }
}
