package com.realpage.otis.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages="com.realpage")
@Import({SecurityConfig.class, WebConfig.class})
public class AppConfig {
	
	@Bean(name="mpfUiJdbcTemplate")
	public JdbcTemplate getYsAnalysisJdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(mpfuiDataSource());
		return template;
	}
	
	@Bean(name = "mpfuiDs")
	@Primary
    @ConfigurationProperties(prefix = "datasource.mpfuiDs")
    public HikariDataSource mpfuiDataSource() {
        HikariDataSource ds = new HikariDataSource();
        return ds;
    } 

}
