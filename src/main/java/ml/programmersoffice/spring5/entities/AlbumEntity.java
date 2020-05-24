package ml.programmersoffice.spring5.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "albums")
public class AlbumEntity {
    @Id
    private Integer id;
    private String albumname;
    private String images;
    private String url;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYoutubeEmbUrl() {
        return url.replace("https://youtu.be/", "https://www.youtube.com/embed/");
    }
    public String getYoutubeThumb() {
        return (url == null || url.length() == 0) ? null : url.replace("https://youtu.be/", "http://img.youtube.com/vi/")
        .concat("/default.jpg");
    }
}
