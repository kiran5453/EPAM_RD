package com.epam;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatasourceConfig {
	    @Bean
	    public DataSource datasource() {
	        return DataSourceBuilder.create()
	                .driverClassName("com.mysql.cj.jdbc.Driver")
	                .url("jdbc:mysql://localhost:3306/vmsdb")
	                .username("root")
	                .password("Epam123$$")
	                .build();
	    }
}