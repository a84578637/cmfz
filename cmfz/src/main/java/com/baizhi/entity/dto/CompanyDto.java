package com.baizhi.entity.dto;

import com.baizhi.entity.fCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Integer total;
    private List<fCompany> rows;
}
