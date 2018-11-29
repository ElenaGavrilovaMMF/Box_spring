package by.iba.box.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"by.iba.box"})
@Import(value = {PersistenceConfig.class, ThymeleafConfig.class, EntityConfig.class, ApplicationConfig.class, BoxApplicationConfig.class})
public class WebConfig {

}
