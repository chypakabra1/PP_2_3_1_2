package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Configuration
//@EnableJpaRepositories("com.goodico.remindme.server.repository")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "web")
//@ComponentScan("com.goodico.remindme.server")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setGenerateDdl(true);
      em.setJpaVendorAdapter(vendorAdapter);
      em.setPackagesToScan("web");

      Properties props=new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.dialect",env.getProperty("hibernate.dialect"));

      em.setJpaProperties(props);

      return em;
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {

      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
      return transactionManager;
   }

   public Properties getHibernateProperties() {
      try {
         Properties properties = new Properties();
         InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
         properties.load(is);
         return properties;
      } catch (IOException e) {
         throw new IllegalArgumentException("hibernate.properties не найден", e);
      }
   }
}
