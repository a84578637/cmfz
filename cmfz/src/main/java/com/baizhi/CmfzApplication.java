package com.baizhi;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan({"com.baizhi.mapper","com.baizhi.shiro.mapper"})


public class CmfzApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmfzApplication.class, args);
    }

}

