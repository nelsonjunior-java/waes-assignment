package com.waes.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DecoderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecoderServiceApplication.class, args);
    }
}
