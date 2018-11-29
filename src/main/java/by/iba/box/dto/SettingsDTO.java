package by.iba.box.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SettingsDTO {

    private String clientID;
    private String clientSecret;
    private AuthBoxDTO appAuth;
}
