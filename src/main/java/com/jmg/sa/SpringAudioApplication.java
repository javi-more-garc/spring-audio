package com.jmg.sa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Javier Moreno Garcia
 *
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EntityScan(basePackages = "com.jmg.sa.domain")
@EnableJpaRepositories(basePackages = "com.jmg.sa.repository")
@EnableTransactionManagement
public class SpringAudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAudioApplication.class, args);
    }
}
