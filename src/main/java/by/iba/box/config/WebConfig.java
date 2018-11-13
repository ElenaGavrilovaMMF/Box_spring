package by.iba.box.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"by.iba.box"})
@Import(value = {ThymeleafConfig.class, EntityConfig.class, ApplicationConfig.class})
public class WebConfig {

}
