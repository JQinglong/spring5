package ml.programmersoffice.spring5.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "words")
public class WordEntity {
    @Id
    private Integer id;
    private String title;
    private String word;
    private String description;

}
