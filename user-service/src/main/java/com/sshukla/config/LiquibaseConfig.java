package com.sshukla.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

@Configuration
public class LiquibaseConfig {

	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/user_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}
}
