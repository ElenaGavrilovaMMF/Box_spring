package by.iba.box.config;

import by.iba.box.entity.ConfigType;
import by.iba.box.entity.JsonBox;
import by.iba.box.entity.TypeJSON;
import by.iba.box.parser.JsonParser;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.nio.charset.StandardCharsets;

import com.box.sdk.BoxConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoxApplicationConfig {

    @Autowired
    JsonParser jsonParser;

    @Bean
    public BoxConfig getBox() throws IOException {
        String json = jsonParser.parseJSON(TypeJSON.AUTH_BOX_CONFIG);
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("96007721_9ihp7m85_config.json");
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        System.out.println(json);
         Reader reader = new InputStreamReader(stream);
        return BoxConfig.readFrom(reader);
    }

    @Bean("api")
    public BoxDeveloperEditionAPIConnection getClient() throws IOException {
        int MAX_CACHE_ENTRIES = 100;
        IAccessTokenCache accessTokenCache = new
                InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
        return BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(getBox(), accessTokenCache);
    }
}
