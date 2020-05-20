package ml.programmersoffice.spring5.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "names")
public class NameEntity {
    @Id
    private Integer key;
    private String name;
}
