
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.MovieJsonInjectType;
import models.MovieJsonInjectValue;
import models.MovieSimple;
import models.MovieJsonProperty;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class JacksonDeserializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSimple() throws IOException {

        String json = new JSONObject()
                .put("title", "Gladiator")
                .put("releaseYear", 2000)
                .put("actors", Arrays.asList("Russell Crowe", "Joaquin Phoenix", "Jennifer Connelly"))
                .toString();

        MovieSimple movie = objectMapper.readValue(json, MovieSimple.class);

        assertEquals("Gladiator", movie.getTitle());
        assertEquals(2000, movie.getReleaseYear());
        assertEquals(3, movie.getActors().size());
    }

    @Test
    public void testSimple_whenJsonMissingAField() throws IOException {

        String json = new JSONObject()
                .put("title", "Gladiator")
                .put("actors", Arrays.asList("Russell Crowe", "Joaquin Phoenix", "Jennifer Connelly"))
                .toString();

        MovieSimple movie = objectMapper.readValue(json, MovieSimple.class);

        assertEquals("Gladiator", movie.getTitle());
        // fails because release year is set to java default of 0
        // not easy to work out how to ignore field in java object deserialisation if missing in json
        assertNull( movie.getReleaseYear());
    }

    @Test
    public void testJsonProperty() throws IOException {

        String json = new JSONObject()
                .put("name", "Gladiator")
                .toString();

        MovieJsonProperty movie = objectMapper.readValue(json, MovieJsonProperty.class);

        assertEquals("Gladiator", movie.getTitle());

    }

    @Test
    public void testJsonInjectByValue() throws IOException {

        String json = new JSONObject()
                .put("title", "Gladiator")
                .toString();

        InjectableValues injectableValues = new InjectableValues.Std().addValue("mediaType", "film");
        objectMapper.setInjectableValues(injectableValues);

        MovieJsonInjectValue movie = objectMapper.readValue(json, MovieJsonInjectValue.class);

        assertEquals("Gladiator", movie.getTitle());
        assertEquals("film", movie.getMediaType());

    }

    @Test
    public void testJsonInjectByType() throws IOException {

        String json = new JSONObject()
                .put("title", "Gladiator")
                .toString();

        InjectableValues injectableValues = new InjectableValues.Std().addValue(LocalDate.class, LocalDate.of(2000, 01, 01));
        objectMapper.setInjectableValues(injectableValues);

        MovieJsonInjectType movie = objectMapper.readValue(json, MovieJsonInjectType.class);

        assertEquals("Gladiator", movie.getTitle());
        assertEquals(2000, movie.getReleaseDate().getYear());

    }



}
