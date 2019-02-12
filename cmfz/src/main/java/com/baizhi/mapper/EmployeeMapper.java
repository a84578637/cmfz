package com.baizhi.mapper;

import com.baizhi.entity.Menu;
import com.baizhi.entity.ProvinceJson;
import com.baizhi.entity.fEmployee;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmployeeMapper extends Mapper<fEmployee> {
    public List<fEmployee> selectUnused(@Param("page") Integer page, @Param("rows") Integer rows);
    public List<fEmployee> selectUsed(@Param("page") Integer page, @Param("rows")Integer rows,@Param("userId") Integer userId);
    public List<ProvinceJson> getEmpProvince();
    public List<ProvinceJson> getEmpProvinceMan();
    public List<ProvinceJson> getEmpProvinceWoMan();
    public Integer getEmpCount(@Param("userId") Integer userid);

}
