package tw.group5.config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "tw.group5")
@EnableTransactionManagement //允許交易
public class RootAppConfig {
	
	// 新增datasource
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();  //new一個JndiObjectFactoryBean
		jndiBean.setJndiName("java:comp/env/connectSQLServerJdbc/OrderService"); //set jndi Name
		jndiBean.afterPropertiesSet(); //當所有設定完成後再取得DataSource
		DataSource ds = (DataSource)jndiBean.getObject(); //getObject後強制轉型成DataSource
		return ds; //目前的bean name就是dataSource
	}
	
	// 新增sessionFactory
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean(); //new一個LocalSessionFactoryBean
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("tw.group5"); //可放字串陣列
		factory.setHibernateProperties(addProperties()); //裡面要放一個properties物件，等下會new一個properties給他
		return factory;
	}
	
	//new sessionFactory會用的方法
	private Properties addProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		properties.put("hibernate.show_sql", Boolean.TRUE);
		properties.put("hibernate.format_sql", Boolean.TRUE);
		return properties;	
	}
	
	//new 交易管理(由他去幫我們管交易)
	@Bean(name = "transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) { //因為上面的LocalSessionFactoryBean不是SessionFactory型別，所以直接用autowired請他自己處理，就不用強迫轉型了
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}

}
