package by.iba.box.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "APP_SETTINGS", schema = "BOX_CONFIG")
public class AppSetting extends BaseEntity<Integer>{

    @Column(name = "CLIENT_ID", unique = true, nullable = false)
    private String clientID;

    @Column(name = "CLIENT_SECRET", nullable = false)
    private String clientSecret;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTH_ID")
    private AuthBox appAuth;
}
