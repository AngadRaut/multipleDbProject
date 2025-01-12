package com.lcwd.db.multi_db.postgres.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "postgresqlLocalFactoryBean"
                         ,basePackages = "com.lcwd.db.multi_db.postgres.repo",
                          transactionManagerRef = "postgresqlTransactionManager")
public class PostgresqlDbConfig {

    @Autowired
    private Environment environment;

    // datasource
    @Bean
    public DataSource postgresqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("second.datasource.url"));
        dataSource.setUsername(environment.getProperty("second.datasource.username"));
        dataSource.setPassword(environment.getProperty("second.datasource.password"));
        return dataSource;
    }

    // entityManagerFactoryBean
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresqlLocalFactoryBean(){
        LocalContainerEntityManagerFactoryBean lc = new LocalContainerEntityManagerFactoryBean();
        lc.setPackagesToScan("com.lcwd.db.multi_db.postgres.entities");

       /* HashMap<String,String> map = new HashMap<>();
        map.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        map.put("hibernate.show_sql","true");
        map.put("hibernate.hbm2ddl.auto","update");*/

        Map<String, String> hibernateProperties = new HashMap<>();

        // Setting the Hibernate Dialect for PostgreSQL
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        // Enabling SQL query logging
        hibernateProperties.put("hibernate.show_sql", "true");

        // Auto schema creation or update
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");

        // Optional: Format the SQL output for better readability
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("spring.jpa.properties.hibernate.jdbc.time_zone","UTC");

        lc.setJpaPropertyMap(hibernateProperties);
        lc.setDataSource(postgresqlDataSource());
        lc.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return lc;
    }
    //transaction
    @Bean
    public PlatformTransactionManager postgresqlTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresqlLocalFactoryBean().getObject());
        return transactionManager;
    }
}
