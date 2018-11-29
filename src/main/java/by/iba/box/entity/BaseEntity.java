package by.iba.box.entity;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@MappedSuperclass
public class BaseEntity <PK extends Serializable> {

    @Id
    @GeneratedValue( strategy  =  GenerationType.IDENTITY)
    @Column(unique = true, name = "ID")
    private PK id;
}
