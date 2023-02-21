package com.jitgur.mall.mbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jitgur.mall")
public class MallMbgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMbgApplication.class, args);
    }

}
