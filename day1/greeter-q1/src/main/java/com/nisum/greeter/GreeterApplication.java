package com.nisum.greeter;

import com.nisum.greeter.service.Greeter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication          // triggers component scan + auto‑configs
public class GreeterApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(GreeterApplication.class, args);

        Greeter greeter = ctx.getBean(Greeter.class);
        System.out.println(greeter.greet("Anurag"));
    }
}
