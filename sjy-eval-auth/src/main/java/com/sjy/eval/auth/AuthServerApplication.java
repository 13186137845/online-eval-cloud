package com.sjy.eval.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: online-eval-clould
 * @description:
 * @author: Created by youxun
 * @create: 2018-12-17 16:27
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.sjy.eval.auth.mapper")
@EnableSwagger2
@EnableFeignClients(basePackages = {"com.codingapi.tx"})
public class AuthServerApplication {
    public static void main(String[] args) {
      SpringApplication.run(AuthServerApplication.class, args);
    }

}
