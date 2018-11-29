package by.iba.box.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "JSON_BOX", schema = "BOX_CONFIG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JsonBox extends BaseEntity<Integer>{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SETTINGS_ID")
    private AppSetting boxAppSettings;

    @Column(name = "ENTERPRISE_ID", nullable = false)
    private String enterpriseID;

}
