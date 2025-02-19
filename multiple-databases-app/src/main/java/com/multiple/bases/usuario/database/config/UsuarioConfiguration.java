package com.multiple.bases.usuario.database.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.multiple.bases.usuario.database.entity.Usuario;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.multiple.bases.usuario.database",
        entityManagerFactoryRef = "usuarioEntityManagerFactory",
        transactionManagerRef= "usuarioTransactionManager"
)
public class UsuarioConfiguration {
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.usuario")
	public DataSourceProperties usuarioDataSourceProperties() {
	    return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.usuario.configuration")
	public DataSource usuarioDataSource() {
		return usuarioDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean(name = "usuarioEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean usuarioBaseEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(usuarioDataSource()).packages(Usuario.class).build();
	}
	
	@Bean(name = "usuarioTransactionManager")
	@Primary
	public PlatformTransactionManager usuarioTransactionManager(
	        final @Qualifier("usuarioEntityManagerFactory") LocalContainerEntityManagerFactoryBean usuarioEntityManagerFactory) {
	    return new JpaTransactionManager(usuarioEntityManagerFactory.getObject());
	}
	
}	