package com.baizhi.entity.dto;

import com.baizhi.entity.fCompany;
import com.baizhi.shiro.entity.secUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class secUserDto {
    private Integer total;
    private List<secUser> rows;
}
