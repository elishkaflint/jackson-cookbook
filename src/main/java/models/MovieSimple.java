package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class MovieSimple {

    private String title;

    private int releaseYear;

    private List<String> actors;

    public MovieSimple() {}

    public MovieSimple(String title, int releaseYear, List<String> actors) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getActors() {
        return actors;
    }

    @Override
    public String toString() {
        return "MovieSimple{" +
                "title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", actors=" + actors +
                '}';
    }
}
