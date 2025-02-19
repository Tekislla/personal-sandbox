package com.multiple.bases.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class UsuarioLiquibaseConfig {
	
	@Bean("usuarioLiquibaseProperties")
    @ConfigurationProperties(prefix = "spring.liquibase")
    public LiquibaseProperties usuarioLiquibaseProperties() {
        return new LiquibaseProperties();
    }
	
	@Bean("usuarioLiquibase")
	public SpringLiquibase usuarioLiquibase(@Qualifier("usuarioDataSource") DataSource dataSource, @Qualifier("usuarioLiquibaseProperties") LiquibaseProperties properties) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog(properties.getChangeLog());
		liquibase.setContexts(properties.getContexts());
		liquibase.setDefaultSchema(properties.getDefaultSchema());
		liquibase.setDropFirst(properties.isDropFirst());
		liquibase.setShouldRun(properties.isEnabled());
		liquibase.setLabels(properties.getLabels());
		liquibase.setChangeLogParameters(properties.getParameters());
		liquibase.setRollbackFile(properties.getRollbackFile());
		return liquibase;
	}
	
	
	
}
