package by.iba.box.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "CONFIGURATION", schema = "BOX_CONFIG")
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigType {

    @Id
    @Column(name = "NAME", nullable = false)
    private String nameTypeJSON;


    @JoinColumn(name = "JSON_ID")
    @OneToOne
    private JsonBox jsonBox;

}
