package jp.co.chronos.hello.configuration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"jp.co.chronos.hello.domain"})
public class ApplicationPersistenceConfiguration extends JpaBaseConfiguration {
    protected ApplicationPersistenceConfiguration(DataSource dataSource, JpaProperties properties,
                                                  ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
                                                  ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
    }
    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }
    @Bean
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
        LocalContainerEntityManagerFactoryBean factory = super.entityManagerFactory(factoryBuilder);
        factory.setPersistenceXmlLocation("classpath:META-INF/orm.xml");
        return factory;
    }
    @Override
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> vendorProperties = new HashMap<>();
        Properties eclipselinkConfig = new Properties();
        try {
            eclipselinkConfig.load(this.getClass().getResourceAsStream("/eclipselink.properties"));
        } catch (IOException e) {
            return vendorProperties;
        }
        vendorProperties.put(EclipseLinkConfiguration.TEMPORAL_MUTABLE.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.TEMPORAL_MUTABLE.getId()));
        vendorProperties.put(EclipseLinkConfiguration.CACHE_SHARED_DEFAULT.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.CACHE_SHARED_DEFAULT.getId()));
        vendorProperties.put(EclipseLinkConfiguration.CACHE_USAGE.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.CACHE_USAGE.getId()));
        vendorProperties.put(EclipseLinkConfiguration.TARGET_DATABASE.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.TARGET_DATABASE.getId()));
        vendorProperties.put(EclipseLinkConfiguration.WEAVING.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.WEAVING.getId()));
        vendorProperties.put(EclipseLinkConfiguration.LOGGING_LEVEL.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.LOGGING_LEVEL.getId()));
        vendorProperties.put(EclipseLinkConfiguration.JDBC_FETCH_SIZE.getId(), eclipselinkConfig.get(EclipseLinkConfiguration.JDBC_FETCH_SIZE.getId()));
        return vendorProperties;
    }
    @Override
    protected String[] getPackagesToScan() {
        return new String[] {"jp.co.chronos.hello.domain"};
    }
}
