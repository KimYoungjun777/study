package dev.mvc.order_v1sbm3c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.mvc"})
public class OrderV1sbm3cApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderV1sbm3cApplication.class, args);
    }

}