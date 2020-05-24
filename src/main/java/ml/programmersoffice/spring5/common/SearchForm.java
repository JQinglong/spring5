package ml.programmersoffice.spring5.common;

import lombok.Data;
@Data
public class SearchForm {
    private int page;
    private String title="";
    private String lyrics="";
}