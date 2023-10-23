package com.example.noticeBoard.config.appconfig;

import com.example.noticeBoard.NoticeBoardApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NoticeBoardApplication.class)
public class AppConfig {
}