package by.iba.box.dto;

import lombok.*;

import javax.persistence.Column;
import javax.xml.ws.WebServiceRef;

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
