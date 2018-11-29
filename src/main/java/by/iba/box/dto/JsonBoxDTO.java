package by.iba.box.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class JsonBoxDTO {

    private SettingsDTO boxAppSettings;
    private String enterpriseID;
}
