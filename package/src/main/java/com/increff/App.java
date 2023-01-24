package com.increff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.increff.grocery-point.*","com.increff.invoice.*"})
@EnableJpaRepositories(basePackages={"com.increff.grocery-point.*","com.increff.invoice.*"})
@EntityScan(basePackages={"com.increff.grocery-point.*","com.increff.invoice.*"})
public class App extends SpringBootServletInitializer {
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}