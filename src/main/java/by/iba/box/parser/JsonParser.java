package by.iba.box.parser;

import by.iba.box.entity.JsonBox;
import by.iba.box.entity.TypeJSON;
import by.iba.box.repository.ConfigRepository;
import by.iba.box.dto.AuthBoxDTO;
import by.iba.box.dto.JsonBoxDTO;
import by.iba.box.dto.SettingsDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor()
@Service
public class JsonParser {

    @Autowired
    ConfigRepository configRepository;

    private static final String REGEX = "\"privateKey\":\"([^\"]*)+\"";

    public String parseJSON(TypeJSON typeJSON) {
        String jsonResult = null;
        if (typeJSON.equals(TypeJSON.AUTH_BOX_CONFIG)) {
            JsonBox authBoxConfig = configRepository.findByNameTypeJSON(typeJSON.name()).getJsonBox();
            jsonResult = getJsonBoxAuthConfig(authBoxConfig);
        }
        return jsonResult;
    }

    private String getJsonBoxAuthConfig(JsonBox authBoxConfig) {
        Gson gson = new Gson();
        JsonBoxDTO jsonBoxDTO = JsonBoxDTO.builder().boxAppSettings(
                SettingsDTO.builder()
                        .clientID(authBoxConfig.getBoxAppSettings().getClientID())
                        .clientSecret(authBoxConfig.getBoxAppSettings().getClientSecret())
                        .appAuth(AuthBoxDTO.builder()
                                .publicKeyID(authBoxConfig.getBoxAppSettings().getAppAuth().getPublicKeyID())
                                .privateKey(authBoxConfig.getBoxAppSettings().getAppAuth().getPrivateKey())
                                .passphrase(authBoxConfig.getBoxAppSettings().getAppAuth().getPassphrase())
                                .build())
                        .build())
                .enterpriseID(authBoxConfig.getEnterpriseID())
                .build();
        String fgfd = jsonBoxDTO.toString();
        String json = gson.toJson(jsonBoxDTO);
        JsonObject obj = new Gson().fromJson(fgfd, JsonObject.class);
        System.out.println(json);
        String s = obj.toString();
        System.out.println(s);
        return getJson(jsonBoxDTO, json);
    }

    private String getJson(JsonBoxDTO jsonBoxDTO, String json) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(json);
        String replaceString = null;
        if (m.find()) {
            replaceString = m.group();
        }
        assert replaceString != null;
        return json.replace(replaceString, "\"privateKey\":\"" + jsonBoxDTO.getBoxAppSettings().getAppAuth().getPrivateKey() + "\"");
    }
}