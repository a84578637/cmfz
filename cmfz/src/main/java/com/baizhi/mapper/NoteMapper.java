package com.baizhi.mapper;

import com.baizhi.entity.fNote;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.Banner;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NoteMapper extends Mapper<fNote> {
    public List<Banner> getAppBanner(@Param("count") Integer count,@Param("page") Integer page, @Param("rows") Integer rows);
}
