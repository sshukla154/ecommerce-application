package com.sshukla.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by `Seemant Shukla` on 07-05-2023
 */

/**
 * This class represents the configuration for Liquibase, a database migration tool.
 */

@Configuration
public class LiquibaseConfig {

	/**
	 * This method creates a SpringLiquibase bean to perform database migration.
	 *
	 * @return the SpringLiquibase bean
	 */
	@Bean
	public SpringLiquibase liquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("/db/changelog/db.changelog-master.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	/**
	 * This method creates a DataSource bean to connect to the database.
	 *
	 * @return the DataSource bean
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/user_service");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}
}
