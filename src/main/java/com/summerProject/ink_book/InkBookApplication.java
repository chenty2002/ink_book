package com.summerProject.ink_book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class InkBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(InkBookApplication.class, args);
        log.info("INK BOOK BACKEND RUNNING");
    }

}
