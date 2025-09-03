package com.callistocode443.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

@Configuration
@ComponentScan("com.callistocode443")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
