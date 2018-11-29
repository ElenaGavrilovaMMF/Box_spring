package by.iba.box.service;

import by.iba.box.entity.ConfigType;
import by.iba.box.entity.TypeJSON;
import by.iba.box.repository.ConfigRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    public void getConfig(){
        System.out.println(configRepository);
        ConfigType byNameTypeJSON = configRepository.findByNameTypeJSON(TypeJSON.AUTH_BOX_CONFIG.name());
        System.out.println(byNameTypeJSON.toString());
    }
}
