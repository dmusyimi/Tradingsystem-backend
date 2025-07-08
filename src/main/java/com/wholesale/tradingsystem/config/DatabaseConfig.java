package com.wholesale.tradingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.wholesale.tradingsystem.repository")
@EnableJpaAuditing
public class DatabaseConfig {
    // Database-specific beans can be defined here if needed
}
