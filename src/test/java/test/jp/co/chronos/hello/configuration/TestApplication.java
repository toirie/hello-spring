package test.jp.co.chronos.hello.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan(
        scopedProxy= ScopedProxyMode.TARGET_CLASS,
        basePackages={"jp.co.chronos.hello.domain",
                "jp.co.chronos.hello.controller"})
public class TestApplication extends WebMvcConfigurerAdapter {
}
