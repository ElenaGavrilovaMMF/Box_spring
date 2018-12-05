package by.iba.box.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class JsonBoxDTO implements Serializable {

    private SettingsDTO boxAppSettings;
    private String enterpriseID;
}
