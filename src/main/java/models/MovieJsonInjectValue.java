package models;

import com.fasterxml.jackson.annotation.JacksonInject;

public class MovieJsonInjectValue {

    private String title;

    @JacksonInject("mediaType")
    private String mediaType;

    public String getTitle() {
        return title;
    }

    public String getMediaType() {
        return mediaType;
    }
}
