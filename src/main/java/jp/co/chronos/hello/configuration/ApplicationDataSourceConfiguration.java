package jp.co.chronos.hello.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ApplicationDataSourceConfiguration {
    @Value("${datasource.user}")
    private String user;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.driver.class.name}")
    private String driverClassName;
    @Value("${datasource.idle}")
    private String idle;
    @Value("${datasource.pool.size}")
    private String poolSize;

    @Bean
    public DataSource dataSource() throws SQLException {
        return getHikariDataSource();
    }

    @Bean
    public TransactionInterceptor txAdvice(PlatformTransactionManager transactionManager) {
        Properties attributes = new Properties();
        attributes.setProperty("*", "PROPAGATION_REQUIRED,-java.lang.Throwable");
        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();
        nameMatchTransactionAttributeSource.setProperties(attributes);
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, nameMatchTransactionAttributeSource);
        return txAdvice;
    }

    @Bean
    public DefaultPointcutAdvisor txAdvisor(TransactionInterceptor txAdvice) {
        DefaultPointcutAdvisor txAdvisor = new DefaultPointcutAdvisor();
        txAdvisor.setAdvice(txAdvice);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* jp.co.chronos.hello.domain..*(..))");
        txAdvisor.setPointcut(pointcut);
        txAdvisor.setOrder(10);
        return txAdvisor;
    }

    private HikariDataSource getHikariDataSource() throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(this.driverClassName);
        config.setJdbcUrl(this.url);
        config.setUsername(this.user);
        config.setPassword(this.password);
        config.setMinimumIdle(new Integer(this.idle));
        config.setMaximumPoolSize(new Integer(this.poolSize));
        return new HikariDataSource(config);
    }
}
