package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.dto.EmployeeDto;
import com.baizhi.entity.fEmployee;
import com.baizhi.service.EmployeeService;
import com.baizhi.service.WorkService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
@Autowired
    EmployeeService employeeService;
@Autowired
    WorkService workService;
@RequestMapping("/addWork")
public String addWork(fEmployee employee,String compid){
    System.out.println(employee+"---- compid is : "+compid);
    workService.addWork(employee,Integer.parseInt(compid));
    return "success";
}

@RequestMapping("/searchEmpByPhone")
public String searchEmpByPhone(String phone){
    String s = employeeService.searchEmpByPhone(phone);
    return s;
}
@RequestMapping("/queryByPhone")
public fEmployee queryByPhone(String phone){
    System.out.println(phone);
    fEmployee byPhone = employeeService.getByPhone(phone);
    System.out.println(byPhone);
    return byPhone;
}

@RequestMapping("/showUpdateEmp")
public String showUpdateEmp(fEmployee employee){
    System.out.println(employee);
    String s = employeeService.updateEmp(employee);
    return s;
}


    @RequestMapping("/empProvinces")
    public Map<String, List<ProvinceJson>> userProvinces() {
        Map<String, List<ProvinceJson>> userProvince = employeeService.getEmpProvince();
        return userProvince;

    }

@RequestMapping("/getMyEmp")
public EmployeeDto getMyEmp(Integer page,Integer rows){

    EmployeeDto used = employeeService.getUsed(page, rows);
    return used;

}


    @RequestMapping("/getUnused")
    public EmployeeDto getUnused(Integer page,Integer rows){
        System.out.println(page+"----"+rows);
        EmployeeDto unused = employeeService.getUnused(page, rows);

        return unused;
    }
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id){
        String s = employeeService.delUser(id);
        return s;
    }
    @RequestMapping("/regist")
    public String regist(fEmployee employee){

        String s=employeeService.addEmp(employee);
        return s;
    }
    @RequestMapping("/poi")
    public void poi(HttpServletResponse response) {
        List<fEmployee> poi = employeeService.getPOI();

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("人员列表", "人员"), fEmployee.class, poi);

        try {
            String encode = URLEncoder.encode("user.xls", "UTF-8");
            response.setHeader("content-disposition","attachment;filename="+encode);
            response.setContentType("application/vnd.ms-excel");

            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
