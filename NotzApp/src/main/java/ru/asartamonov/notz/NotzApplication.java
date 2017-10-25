package ru.asartamonov.notz;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import configuration.ModelConfig;
import ru.asartamonov.notz.config.WebMvcConfig;
import ru.asartamonov.notz.config.WebSecurityConfig;

@SpringBootApplication(scanBasePackages = { "ru.asartamonov" })
@EnableJpaRepositories(basePackages = { "repository" })
@EntityScan(basePackages = { "domain" })
@Import({ModelConfig.class, WebSecurityConfig.class, WebMvcConfig.class})
public class NotzApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotzApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	@FlywayDataSource
	@ConfigurationProperties(prefix = "datasource.flyway")
	public DataSource flywayDataSource() {
		return DataSourceBuilder.create().build();
	}
}
