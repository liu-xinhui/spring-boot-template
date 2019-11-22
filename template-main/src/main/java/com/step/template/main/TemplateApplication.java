package com.step.template.main;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@AllArgsConstructor
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class TemplateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        log.info("-------------------平台启动成功-------------------");
    }

}
