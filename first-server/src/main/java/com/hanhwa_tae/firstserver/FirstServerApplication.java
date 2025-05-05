package com.hanhwa_tae.firstserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FirstServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstServerApplication.class, args);
    }

}
