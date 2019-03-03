package com.vahap.twodatasource.conf;

import com.vahap.twodatasource.util.DatasourceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 12.10.2018
 */
@Configuration
@PropertySource("classpath:datasource-opus.properties")
public class OpusDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource opusDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.opus.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.opus.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.opus.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.opus.datasource.password"));
        return dataSource;
    }

    @Bean(name = "opusEntityManager")
    public LocalContainerEntityManagerFactoryBean opusEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(opusDatasource());

        em.setPackagesToScan(new String[]{DatasourceConstants.PACKAGE_OPUS});

        em.setPersistenceUnitName(DatasourceConstants.JPA_UNIT_NAME_OPUS);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("spring.opus.jpa.properties.hibernate.dialect"));
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("spring.opus.jpa.show-sql"));
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.opus.jpa.hibernate.ddl-auto"));
        properties.put(org.hibernate.cfg.Environment.IMPLICIT_NAMING_STRATEGY, env.getProperty("spring.opus.jpa.hibernate.naming.implicit-strategy"));
        properties.put(org.hibernate.cfg.Environment.PHYSICAL_NAMING_STRATEGY, env.getProperty("spring.opus.jpa.hibernate.naming.physical-strategy"));
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean(name = "opusTransactionManager")
    public PlatformTransactionManager opusTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(opusEntityManager().getObject());
        return transactionManager;
    }
}
