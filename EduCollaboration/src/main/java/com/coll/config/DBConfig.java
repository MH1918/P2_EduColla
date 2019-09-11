package com.coll.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.coll.model.Blog;
import com.coll.model.BlogComment;
import com.coll.model.Forum;
import com.coll.model.ForumComment;
import com.coll.model.Friend;
import com.coll.model.Job;
import com.coll.model.ProfilePicture;
import com.coll.model.UserDetail;

@Configuration
@ComponentScan("com.coll")
@EnableTransactionManagement
public class DBConfig {
	
		public DataSource getOracleDataSource() {
		
			DriverManagerDataSource dataSource=new DriverManagerDataSource();
			
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl11");
			dataSource.setUsername("educollaboration");
			dataSource.setPassword("pass123");
			
			System.out.println("~~~~ DataSource Created ~~~~");
			
			return dataSource;
		}
		
		//Create a Bean for the SessionFactory
		@Bean(name="sessionFactory")
		public SessionFactory getSessionFactory() {
			
			Properties hibernateProperties=new Properties();
			hibernateProperties.put("hibernate.hbmddl2.auto", "update");
			hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			
			LocalSessionFactoryBuilder factory=new LocalSessionFactoryBuilder(getOracleDataSource());
			factory.addProperties(hibernateProperties);
			
			
			factory.addAnnotatedClass(UserDetail.class);
			factory.addAnnotatedClass(Blog.class);
			factory.addAnnotatedClass(BlogComment.class);
			factory.addAnnotatedClass(Job.class);
			factory.addAnnotatedClass(Friend.class);
			factory.addAnnotatedClass(Forum.class);
			factory.addAnnotatedClass(ForumComment.class);
			factory.addAnnotatedClass(ProfilePicture.class);
			
			
			SessionFactory sessionFactory=factory.buildSessionFactory();
			
			System.out.println("~~~~ SessionFactory Implementation ~~~~");
			
			return sessionFactory;
		}
		
		//Create a Bean for HibernateTransactionManager
		@Bean
		public HibernateTransactionManager getHibernateTransactionManger(SessionFactory sessionFactory) {
			
			System.out.println("~~~~ HibernateTransactionManager ~~~~");
			
			return new HibernateTransactionManager(sessionFactory);	
		}
}
