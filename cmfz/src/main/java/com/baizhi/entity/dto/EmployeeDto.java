package com.baizhi.entity.dto;

import com.baizhi.entity.fEmployee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer total;
    private List<fEmployee> rows;
}
