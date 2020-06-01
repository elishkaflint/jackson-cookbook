package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieJsonProperty {

    private String title;

    public MovieJsonProperty(@JsonProperty("name") String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
