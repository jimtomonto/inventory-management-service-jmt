package com.ibm.inventory_management.app;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan({ "com.ibm.inventory_management.*", "com.ibm.cloud_garage.*", "com.ibm.health" })
public class Application extends SpringBootServletInitializer {
    @Autowired
    Environment environment;

    public static void main(final String[] args) {
        if (args.length > 0) {
            System.out.println(String.format("ERROR: Exiting application because expecting zero arguments but found %s",
                    args.length));
            System.exit(0);
        }
        SpringApplication.run(com.ibm.inventory_management.app.Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {
            final String port = environment.getProperty("local.server.port");

            System.out.println();
            System.out.println("Server started - http://localhost:" + port + "/swagger-ui.html");
        };
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
