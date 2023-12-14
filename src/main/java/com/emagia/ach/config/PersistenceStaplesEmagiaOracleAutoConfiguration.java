package com.emagia.ach.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:StaplesEmagiaOracle-UAT.properties"})
@EnableJpaRepositories(
        basePackages = "com.emagia.ach.staples_emagia.repository",
        entityManagerFactoryRef = "staplesEmagiaOracleEntityManager",
        transactionManagerRef = "staplesEmagiaOracleTransactionManager")
public class PersistenceStaplesEmagiaOracleAutoConfiguration {

    // productEntityManager model
    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.st-datasource")
    public DataSource staplesEmagiaOracleDataSource() {
        return DataSourceBuilder.create().build();
    }


     /*  @Bean
    public DataSource h2DataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.mysql-datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.mysql-datasource.url"));
        dataSource.setUsername(env.getProperty("spring.mysql-datasource.username"));
        dataSource.setPassword(env.getProperty("spring.mysql-datasource.password"));

        return dataSource;
    }*/



    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean staplesEmagiaOracleEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(staplesEmagiaOracleDataSource());
        em.setPackagesToScan(
                new String[] { "com.emagia.ach.entity.staples_emagia" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    // mysqlTransactionManager model
    @Primary
    @Bean
    public PlatformTransactionManager staplesEmagiaOracleTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                staplesEmagiaOracleEntityManager().getObject());
        return transactionManager;
    }
}

