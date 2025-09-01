package com.callistocode443.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.callistocode443")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
