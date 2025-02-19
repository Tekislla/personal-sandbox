package com.multiple.bases.endereco.database.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.multiple.bases.endereco.database.entity.Endereco;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multiple.bases.endereco.database",
        entityManagerFactoryRef = "enderecoEntityManagerFactory",
        transactionManagerRef= "enderecoTransactionManager"
)
public class EnderecoConfiguration {
	
	@Bean
	@ConfigurationProperties("spring.datasource.endereco")
	public DataSourceProperties enderecoDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.endereco.configuration")
	public DataSource enderecoDataSource() {
		return enderecoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "enderecoEntityManagerFactory")
	   public LocalContainerEntityManagerFactoryBean enderecoBaseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
	       return builder.dataSource(enderecoDataSource()).packages(Endereco.class).build();
	 }
	
	@Bean(name = "enderecoTransactionManager")
	public PlatformTransactionManager enderecoTransactionManager(
	        final @Qualifier("enderecoEntityManagerFactory") LocalContainerEntityManagerFactoryBean enderecoEntityManagerFactory) {
	    return new JpaTransactionManager(enderecoEntityManagerFactory.getObject());
	}
	
}
