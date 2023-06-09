package com.spring.mvc;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
public class ApplicationContextConfig {	
	
	@Value("${mysqldriver}")
	private String driver;
	@Value("${mysqlurl}")
	private String url;
	@Value("${mysqluser}")
	private String user;
	@Value("${mysqlpwd}")
	private String password;
	
	
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		System.out.println("view resolver");
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	@Bean
	public DataSource dataSource() {
		System.out.println("creating datasource");
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan("com.spring.mvc.entity");
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		System.out.println("session factory created");
		return sessionFactoryBean;
	}

 
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");

        return hibernateProperties;
    }
    
}
