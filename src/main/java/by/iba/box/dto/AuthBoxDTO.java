package by.iba.box.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthBoxDTO {

    private String publicKeyID;
    private String privateKey;
    private String passphrase;
}
