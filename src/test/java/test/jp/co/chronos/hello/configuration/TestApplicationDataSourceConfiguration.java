package test.jp.co.chronos.hello.configuration;

import jp.co.chronos.hello.configuration.ApplicationDataSourceConfiguration;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
@Configuration
public class TestApplicationDataSourceConfiguration extends ApplicationDataSourceConfiguration {
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
        return getJdbcDataSource();
    }

    private JdbcDataSource getJdbcDataSource() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}
