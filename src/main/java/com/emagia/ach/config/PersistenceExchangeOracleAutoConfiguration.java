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
@PropertySource({"classpath:ExchangeOracle-PROD.properties"})
@EnableJpaRepositories(
        basePackages = "com.emagia.ach.repository",
        entityManagerFactoryRef = "exchangeOracleEntityManager",
        transactionManagerRef = "exchangeOracleTransactionManager")
public class PersistenceExchangeOracleAutoConfiguration {

    // productEntityManager model
    @Autowired
    private Environment env;

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.my-datasource")
    public DataSource exchangeOracleDataSource() {
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
    public LocalContainerEntityManagerFactoryBean exchangeOracleEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(exchangeOracleDataSource());
        em.setPackagesToScan(
                new String[] { "com.emagia.ach.entity" });

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
    public PlatformTransactionManager exchangeOracleTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                exchangeOracleEntityManager().getObject());
        return transactionManager;
    }
}

