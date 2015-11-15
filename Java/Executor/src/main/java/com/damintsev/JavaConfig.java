package com.damintsev;

import com.damintsev.rest.config.RestClientConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 07 ���. 2015
 */
@Configuration
@Import(RestClientConfig.class)
@ComponentScan("com.damintsev")
@PropertySources(value = { @PropertySource("classpath:/application.properties") })
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class JavaConfig {

    @Value("${jdbc.driverClassName}")
    private String KEY_DRIVER_CLASS;

    @Value("${jdbc.url}")
    private String KEY_JDBC_URL;

    @Value("${jdbc.username}")
    private String KEY_JDBC_USERNAME;

    @Value("${jdbc.password}")
    private String KEY_JDBC_PASSWORD;

    @Value("${hibernate.dialect}")
    private String KEY_HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String KEY_HBERNATE_SHOW_SQL;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.TRUE);
        vendorAdapter.setDatabase(Database.POSTGRESQL);


        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.damintsev.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();


        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

        return factory;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(KEY_DRIVER_CLASS);
        dataSource.setUrl(KEY_JDBC_URL);
        dataSource.setUsername(KEY_JDBC_USERNAME);
        dataSource.setPassword(KEY_JDBC_PASSWORD);

        return dataSource;
    }
}
