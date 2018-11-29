package by.iba.box.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUTH", schema = "BOX_CONFIG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AuthBox extends BaseEntity<Integer> {

    @Column(name = "PUBLIC_KEY", unique = true, nullable = false)
    private String publicKeyID;

    @Column(name = "PRIVATE_KEY", nullable = false)
    private String privateKey;

    @Column(name = "PASSPHRASE", nullable = false)
    private String passphrase;
    }
