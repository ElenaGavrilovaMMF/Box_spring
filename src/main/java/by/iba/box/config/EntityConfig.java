package by.iba.box.config;

import by.iba.box.service.Redirector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {

    @Bean
    public Redirector showRedirect(){
        return new Redirector();
    }

}
