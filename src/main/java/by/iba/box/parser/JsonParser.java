//package by.iba.box.parser;
//
//import dao.JsonDao;
//import by.iba.box.dto.AuthBoxDTO;
//import by.iba.box.dto.JsonBoxDTO;
//import by.iba.box.dto.SettingsDTO;
//import by.itacademy.matveyenka.entity.JsonBox;
//import com.google.gson.Gson;
//import lombok.AccessLevel;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor()
//public class JsonParser {
//
//    public  String parseJSON() {
//        Gson gson = new Gson();
//        JsonBox json = new JsonDao().getJson();
//        JsonBoxDTO jsonBoxDTO= JsonBoxDTO.builder().boxAppSettings(
//                SettingsDTO.builder()
//                        .clientID(json.getBoxAppSettings().getClientID())
//                        .clientSecret(json.getBoxAppSettings().getClientSecret())
//                        .appAuth(AuthBoxDTO.builder()
//                                .publicKeyID(json.getBoxAppSettings().getAppAuth().getPublicKeyID())
//                                .privateKey(json.getBoxAppSettings().getAppAuth().getPrivateKey())
//                                .passphrase(json.getBoxAppSettings().getAppAuth().getPassphrase())
//                                .build())
//                        .build())
//                .enterpriseID(json.getEnterpriseID())
//                .build();
//        return gson.toJson(jsonBoxDTO);
//    }
//}
