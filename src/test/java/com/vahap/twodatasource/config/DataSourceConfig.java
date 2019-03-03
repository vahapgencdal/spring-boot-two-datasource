package com.vahap.twodatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
public class DataSourceConfig {

    @Autowired
    private Environment env;

    public DataSourceConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.vahap.twodatasource.model.entity"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    protected Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("spring.database-platform"));
        hibernateProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.put(org.hibernate.cfg.Environment.IMPLICIT_NAMING_STRATEGY, env.getProperty("spring.jpa.hibernate.naming.implicit-strategy"));
        hibernateProperties.put(org.hibernate.cfg.Environment.PHYSICAL_NAMING_STRATEGY, env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
        hibernateProperties.put(org.hibernate.cfg.Environment.DEFAULT_SCHEMA, env.getProperty("spring.jpa.properties.hibernate.default_schema"));
        hibernateProperties.put(org.hibernate.cfg.Environment.USE_SQL_COMMENTS, env.getProperty("spring.jpa.properties.hibernate.use_sql_comments"));
        hibernateProperties.put(org.hibernate.cfg.Environment.FORMAT_SQL, env.getProperty("spring.jpa.properties.hibernate.format_sql"));

        return hibernateProperties;
    }


}
