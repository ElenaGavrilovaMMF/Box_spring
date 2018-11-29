package by.iba.box.config;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;

import java.io.*;

import com.box.sdk.BoxConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoxApplicationConfig {

    @Bean
    public BoxConfig box() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("96007721_9ihp7m85_config.json");
        Reader reader = new InputStreamReader(is);
        return BoxConfig.readFrom(reader);
    }

    @Bean("api")
    public BoxDeveloperEditionAPIConnection client() throws IOException {
        int MAX_CACHE_ENTRIES = 100;
        IAccessTokenCache accessTokenCache = new
                InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
        return BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(box(), accessTokenCache);
    }
}
