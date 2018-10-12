package com.vahap.twodatasource.conf;

import com.vahap.twodatasource.util.DatasourceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
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
 * @description TODO: Class Description
 */
@Configuration
@PropertySources({@PropertySource("classpath:datasource-radar.properties")})
public class RadarDataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource radarDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.radar.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.radar.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.radar.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.radar.datasource.password"));
        return dataSource;
    }


    @Bean(name = "radarEntityManager")
    public LocalContainerEntityManagerFactoryBean radarEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(radarDatasource());

        em.setPackagesToScan(new String[]{DatasourceConstants.PACKAGE_RADAR});

        em.setPersistenceUnitName(DatasourceConstants.JPA_UNIT_NAME_RADAR);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("spring.radar.jpa.properties.hibernate.dialect"));
        properties.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("spring.radar.jpa.show-sql"));
        properties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.radar.jpa.hibernate.ddl-auto"));
        properties.put(org.hibernate.cfg.Environment.IMPLICIT_NAMING_STRATEGY, env.getProperty("spring.radar.jpa.hibernate.naming.implicit-strategy"));
        properties.put(org.hibernate.cfg.Environment.PHYSICAL_NAMING_STRATEGY, env.getProperty("spring.radar.jpa.hibernate.naming.physical-strategy"));
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean(name = "radarTransactionManager")
    public PlatformTransactionManager radarTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(radarEntityManager().getObject());
        return transactionManager;
    }
}
