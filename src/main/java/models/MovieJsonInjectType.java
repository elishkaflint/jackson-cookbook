package models;

import com.fasterxml.jackson.annotation.JacksonInject;

import java.time.LocalDate;

public class MovieJsonInjectType {

    private String title;

    @JacksonInject
    private LocalDate releaseDate;

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
