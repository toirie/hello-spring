package jp.co.chronos.hello.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ResourceBundle;

@EnableWebMvc
@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages", "logging_messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
