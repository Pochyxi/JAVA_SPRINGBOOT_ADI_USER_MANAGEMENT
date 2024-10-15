package com.adi.usermanagement.web;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.adi.usermanagement.*"})
@PropertySource( "classpath:application-secret.properties" )
public class UserManagementApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run( UserManagementApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
