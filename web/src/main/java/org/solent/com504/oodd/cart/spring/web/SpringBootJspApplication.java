package org.solent.com504.oodd.cart.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author ismai
 */
@SpringBootApplication(scanBasePackages = "org.solent.com504.oodd.cart.spring.web")
public class SpringBootJspApplication extends SpringBootServletInitializer {

    /**
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootJspApplication.class);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJspApplication.class);
    }
}