package ml.programmersoffice.spring5.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "hsk")
public class HskEntity {
    @Id
    private Integer id;
    
    private Integer level;
    private String hanzi;
    private String pinyin;
    private String translations;

}
