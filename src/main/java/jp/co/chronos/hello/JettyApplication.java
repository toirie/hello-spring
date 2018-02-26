package jp.co.chronos.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(
        scopedProxy = ScopedProxyMode.TARGET_CLASS,
        basePackages = {"jp.co.chronos.hello"}
)
public class JettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JettyApplication.class, args);
    }
}
