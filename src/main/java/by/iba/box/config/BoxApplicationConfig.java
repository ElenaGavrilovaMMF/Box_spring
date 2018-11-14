package by.iba.box.config;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.box.sdk.BoxConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoxApplicationConfig {

    @Bean
    public BoxConfig box() throws IOException {
        Reader reader = new FileReader("D:/java_progects/Box_spring/src/main/java/config_file/96007721_3jca2dk0_config.json");
        return BoxConfig.readFrom(reader);
    }

    @Bean
    public BoxDeveloperEditionAPIConnection client() throws IOException {
        int MAX_CACHE_ENTRIES = 100;
        IAccessTokenCache accessTokenCache = new
                InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
        return BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(box(), accessTokenCache);
    }
}
