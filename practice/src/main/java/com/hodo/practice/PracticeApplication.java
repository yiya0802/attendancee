package com.hodo.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("src/main/resources/mapper")
public class PracticeApplication
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(PracticeApplication.class, args);
    }
    
}
