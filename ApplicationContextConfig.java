package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.dao.BlogDAOImpl;
import com.niit.collaboration.dao.ChatDAOImpl;
import com.niit.collaboration.dao.EventDAOImpl;
import com.niit.collaboration.dao.ForumDAOImpl;
import com.niit.collaboration.dao.FriendDAOImpl;
import com.niit.collaboration.dao.JobDAOImpl;
import com.niit.collaboration.dao.UserDAOImpl;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit.collaboration")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getOracleDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1522:XE");
		dataSource.setUsername("Project");
		dataSource.setPassword("harika");
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
		   
		

		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connectionProperties.setProperty("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		connectionProperties.setProperty("hibernate.format_sql", "true");
		connectionProperties.setProperty("hibernate.jdbc.use_get_generated_keys", "true");
		//dataSource.setConnectionProperties(connectionProperties);
		return connectionProperties;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		//sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		//sessionBuilder.addAnnotatedClass(Chat.class);
		/*sessionBuilder.addAnnotatedClass(Event.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		*/
		return sessionBuilder.buildSessionFactory();
	}

	
	
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);  //should be same name as bean name of session factory
	return transactionManager;
	}
	/*@Autowired
	@Bean(name = "user")
        public User getUser (){
		return new User();
	}
	
	
	@Autowired
	@Bean(name = "userDAO")
        public UserDAOImpl getUserDAOImpl (SessionFactory sessionFactory){
		return new UserDAOImpl(sessionFactory);
	}*/
	@Autowired
	@Bean(name = "blog")
	public Blog getBlog() {
		return new Blog();
	}
	
	@Autowired
	@Bean(name = "blogDAO")
        public BlogDAOImpl getBlogDAOImpl (SessionFactory sessionFactory){
		return new BlogDAOImpl(sessionFactory);
	}
	/*@Autowired
	@Bean(name = "chat")
	public Chat getChat() {
		return new Chat();
	}
	
	@Autowired
	@Bean(name = "chatDAO")
        public ChatDAOImpl getChatDAOImpl (SessionFactory sessionFactory){
		return new ChatDAOImpl(sessionFactory);
	}*/
	/*@Autowired
	@Bean(name = "event")
	public Event getEvent() {
		return new Event();
	}
	@Autowired
	@Bean(name = "eventDAO")
        public EventDAOImpl getEventDAOImpl (SessionFactory sessionFactory){
		return new EventDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "forum")
	public Forum getForum() {
		return new Forum();
	}
	@Autowired
	@Bean(name = "forumDAO")
        public ForumDAOImpl getForumDAOImpl (SessionFactory sessionFactory){
		return new ForumDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "friend")
	public Friend getFriend() {
		return new Friend();
	}
	@Autowired
	@Bean(name = "friendDAO")
        public FriendDAOImpl getFriendDAOImpl (SessionFactory sessionFactory){
		return new FriendDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "job")
	public Job getJob() {
		return new Job();
	}
	@Autowired
	@Bean(name = "jobDAO")
        public JobDAOImpl getJobDAOImpl (SessionFactory sessionFactory){
		return new JobDAOImpl(sessionFactory);
	}
	*/
	
	


}
