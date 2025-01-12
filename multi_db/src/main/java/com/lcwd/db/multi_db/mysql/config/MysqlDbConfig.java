package com.lcwd.db.multi_db.mysql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlLocalFactoryBean"
        ,basePackages = "com.lcwd.db.multi_db.mysql.repo",
        transactionManagerRef = "mysqlTransactionManager")
public class MysqlDbConfig {
    @Autowired
    private Environment environment;

    // datasource
    @Bean
    @Primary
    public DataSource mysqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    // entityManagerFactoryBean
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlLocalFactoryBean(){
        LocalContainerEntityManagerFactoryBean lc = new LocalContainerEntityManagerFactoryBean();
        lc.setPackagesToScan("com.lcwd.db.multi_db.mysql.entities");

/*        HashMap<String,String> map = new HashMap<>();
        map.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        map.put("hibernate.show_sql","true");
        map.put("hibernate.hbm2ddl.auto","update");*/
        // Creating a HashMap to hold Hibernate configuration properties
        Map<String, String> hibernateProperties = new HashMap<>();
        // Setting Hibernate Dialect for MySQL 8
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        // Enabling SQL query logging
        hibernateProperties.put("hibernate.show_sql", "true");

        // Configuring Hibernate to update the database schema automatically
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.put("spring.jpa.properties.hibernate.jdbc.time_zone","UTC");

        lc.setJpaPropertyMap(hibernateProperties);
        lc.setDataSource(mysqlDataSource());
        lc.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return lc;
    }

    //transaction
    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mysqlLocalFactoryBean().getObject());
        return transactionManager;
    }
}
