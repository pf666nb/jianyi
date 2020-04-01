package com.jianyi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration public class MailConfiguration {
    @Bean
    public JavaMailSenderImpl JavaMailSender(){
        Properties prop = new Properties();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("wpf1340701723@163.com");
        mailSender.setPassword("wswpf1340701723");
        mailSender.setPort(465);

        mailSender.setDefaultEncoding("UTF-8");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        mailSender.setJavaMailProperties(prop);


        return mailSender;
    }
}