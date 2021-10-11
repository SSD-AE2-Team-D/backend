package com.guidelk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class GuideLKApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuideLKApplication.class, args);
    }

}
